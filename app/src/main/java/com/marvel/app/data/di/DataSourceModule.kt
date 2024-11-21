package com.marvel.app.data.di

import com.marvel.app.data.api.MarvelApi
import com.marvel.app.data.repository.MarvelDataSource
import com.marvel.app.data.repository.MarvelDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideForecastDataSourceRemoteDataSource(api: MarvelApi): MarvelDataSource {
        return MarvelDataSourceImp(api)
    }


}