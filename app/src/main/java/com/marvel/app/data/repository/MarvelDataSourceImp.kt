package com.marvel.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marvel.app.common.Constants
import com.marvel.app.common.model.IResult
import com.marvel.app.common.utils.Utils
import com.marvel.app.data.api.MarvelApi
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.comics.ComicsResponse
import com.marvel.app.domain.models.comics.ComicsResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelDataSourceImp @Inject constructor(private val api: MarvelApi) :
    MarvelDataSource {
    override suspend fun getMarvelCharacters(): Flow<PagingData<CharacterResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(api) }
        ).flow
    }

    override suspend fun getMarvelCharactersComics(charaterId: Int): IResult<ComicsResponse> {
        return try {
            val timestamp = System.currentTimeMillis().toString()
            val hash = Utils.generateMarvelHash(timestamp, Constants.privateKey, Constants.publicKey)
            val response =  api.getMarvelCharactersComics(characterId = charaterId , offset =  0 , limit =  10 , hash = hash,timestamp = timestamp, apiKey = Constants.publicKey)

            IResult.success(response)
        }catch (e:Exception){
            IResult.failure(e.localizedMessage)
        }


          }

}