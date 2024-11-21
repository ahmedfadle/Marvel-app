package com.marvel.app.domain.usecases

import com.marvel.app.domain.repositories.MarvelRepository
import javax.inject.Inject

class GetCharactersUseCaseImp  @Inject constructor(private val repository: MarvelRepository):GetCharactersUseCase{
    override suspend operator fun invoke() =  repository.getMarvelCharacters()


}