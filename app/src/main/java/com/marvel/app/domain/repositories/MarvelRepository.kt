package com.marvel.app.domain.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.marvel.app.common.model.IResult
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.comics.ComicsResponse
import com.marvel.app.domain.models.comics.ComicsResult
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun getMarvelCharacters(): Flow<PagingData<CharacterResult>>
    suspend fun getMarvelCharactersComics(characterId:Int): IResult<ComicsResponse>
}