package com.josetorres.marvel.data.datasources.remote.service.retrofit

import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.Result
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("v1/public/characters")
    suspend fun getCharactersList(): NetworkResponse<ApiError, Result>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: String): NetworkResponse<ApiError, Result>

}