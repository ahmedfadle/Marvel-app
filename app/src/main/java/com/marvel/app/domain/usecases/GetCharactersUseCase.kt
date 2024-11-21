package com.marvel.app.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.marvel.app.domain.models.CharacterResult
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {

    suspend fun invoke(): Flow<PagingData<CharacterResult>>


}