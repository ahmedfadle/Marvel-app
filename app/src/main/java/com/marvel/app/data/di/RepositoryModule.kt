package com.marvel.app.data.di

import com.marvel.app.data.repository.MarvelRepositoryImp
import com.marvel.app.data.repository.MarvelDataSource
import com.marvel.app.domain.repositories.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: MarvelDataSource
    ): MarvelRepository {
        return MarvelRepositoryImp(
            remoteDataSource = remoteDataSource
        )
    }
}