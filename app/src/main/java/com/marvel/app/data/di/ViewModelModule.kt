package com.marvel.app.data.di

import com.marvel.app.data.repository.MarvelRepositoryImp
import com.marvel.app.data.repository.MarvelDataSource
import com.marvel.app.data.repository.MarvelDataSourceImp
import com.marvel.app.domain.repositories.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @ForeCastRepositorynterator
    fun provideInLoginDataSource(inLoginDataSource: MarvelRepositoryImp): MarvelRepository

    @Binds
    @provideForecastDataSource
    fun provideForecastDataSource(inLoginDataSource: MarvelDataSourceImp): MarvelDataSource


}
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForeCastRepositorynterator

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class provideForecastDataSource


