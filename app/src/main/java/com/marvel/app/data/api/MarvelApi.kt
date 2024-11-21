package com.marvel.app.data.api

import com.marvel.app.domain.models.CharacterResponse
import com.marvel.app.domain.models.comics.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getMarvelCharacters(
        @Query("apikey") apiKey: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 10,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String
        ): CharacterResponse

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getMarvelCharactersComics(
        @Path("characterId")characterId:Int,
        @Query("apikey") apiKey: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 10,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        ): ComicsResponse


}