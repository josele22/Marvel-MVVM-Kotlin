package com.josetorres.marvel.data.datasources.remote

import com.josetorres.marvel.data.datasources.remote.model.response.*
import com.josetorres.marvel.domain.model.CharacterDomain

interface ICharacterRemoteDataSource {
    suspend fun getCharactersListSource(): NetworkResponse<ApiError.Response, List<CharacterDomain>>
    suspend fun getCharacterDetailSource(id: String): NetworkResponse<ApiError.Response, CharacterDomain>
}