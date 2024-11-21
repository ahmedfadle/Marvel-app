package com.marvel.app.data.di

import com.marvel.app.data.repository.MarvelRepositoryImp
import com.marvel.app.domain.usecases.GetCharactersUseCase
import com.marvel.app.domain.usecases.GetCharactersUseCaseImp
import com.marvel.app.domain.usecases.GetComicsUseCase
import com.marvel.app.domain.usecases.GetComicsUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetCharactersUseCase(repository: MarvelRepositoryImp): GetCharactersUseCase {
        return GetCharactersUseCaseImp(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCharactersComicsUseCase(repository: MarvelRepositoryImp): GetComicsUseCase {
        return GetComicsUseCaseImp(repository)
    }

}