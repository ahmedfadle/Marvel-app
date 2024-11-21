package com.marvel.app.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.marvel.app.common.model.IResult
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.comics.ComicsResponse
import com.marvel.app.domain.models.comics.ComicsResult
import com.marvel.app.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val remoteDataSource: MarvelDataSource
) : MarvelRepository {
    override suspend fun getMarvelCharacters(): Flow<PagingData<CharacterResult>> {
       return remoteDataSource.getMarvelCharacters()
    }

    override suspend fun getMarvelCharactersComics(characterId: Int): IResult<ComicsResponse>{
      return remoteDataSource.getMarvelCharactersComics(characterId)

    }

}