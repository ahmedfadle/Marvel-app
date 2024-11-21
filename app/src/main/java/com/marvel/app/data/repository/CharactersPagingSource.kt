package com.marvel.app.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marvel.app.common.Constants
import com.marvel.app.common.utils.Utils
import com.marvel.app.data.api.MarvelApi
import com.marvel.app.domain.models.CharacterResult
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 0

class CharactersPagingSource(
    private val api: MarvelApi,
) : PagingSource<Int, CharacterResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        val timestamp = System.currentTimeMillis().toString()
        val hash = Utils.generateMarvelHash(timestamp, Constants.privateKey, Constants.publicKey)
        return try {
            var response = api.getMarvelCharacters(
                apiKey = Constants.publicKey, offset = position, limit = 20, hash = hash,timestamp = timestamp
            )

            val totalPages = response.data?.total ?: 0
            val nextKey = if (position >= (totalPages -1)) {
                null
            } else {
                position +1
            }

            LoadResult.Page(
                data = response.data?.results ?: emptyList(),
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey

            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}