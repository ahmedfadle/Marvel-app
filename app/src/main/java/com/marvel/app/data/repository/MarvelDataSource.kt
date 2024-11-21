package com.marvel.app.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.marvel.app.common.model.IResult
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.Comics
import com.marvel.app.domain.models.comics.ComicsResponse
import com.marvel.app.domain.models.comics.ComicsResult
import kotlinx.coroutines.flow.Flow

interface MarvelDataSource {

    suspend fun getMarvelCharacters(): Flow<PagingData<CharacterResult>>
    suspend fun getMarvelCharactersComics(charaterId:Int): IResult<ComicsResponse>



}