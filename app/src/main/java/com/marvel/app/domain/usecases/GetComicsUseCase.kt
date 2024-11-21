package com.marvel.app.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.marvel.app.common.model.IResult
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.comics.ComicsResponse
import com.marvel.app.domain.models.comics.ComicsResult
import kotlinx.coroutines.flow.Flow

interface GetComicsUseCase {

    suspend fun invoke(characterId:Int): IResult<ComicsResponse>


}