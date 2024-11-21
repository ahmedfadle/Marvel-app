package com.marvel.app.domain.usecases

import com.marvel.app.domain.repositories.MarvelRepository
import javax.inject.Inject

class GetComicsUseCaseImp  @Inject constructor(private val repository: MarvelRepository):GetComicsUseCase{
    override suspend operator fun invoke(characterId:Int) =  repository.getMarvelCharactersComics(characterId)



}