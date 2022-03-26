package com.josetorres.marvel.data.datasources.remote

import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.datasources.remote.service.ApiResponseType
import com.josetorres.marvel.data.datasources.remote.service.retrofit.RemoteClient
import com.josetorres.marvel.domain.model.CharacterDomain

class CharacterRemoteDataSourceImpl(private val remoteClient: RemoteClient) :
    ICharacterRemoteDataSource {

    override suspend fun getCharactersListSource(): NetworkResponse<ApiError.Response, List<CharacterDomain>> {

        val response = remoteClient.serviceCharacters.getCharactersList()

        return ApiResponseType().getResponseType(ApiResponseType.ID_CHARACTER_LIST, response) as NetworkResponse<ApiError.Response, List<CharacterDomain>>
    }

    override suspend fun getCharacterDetailSource(id: String): NetworkResponse<ApiError.Response, CharacterDomain> {

        val response = remoteClient.serviceCharacters.getCharacter(id)

        return ApiResponseType().getResponseType(ApiResponseType.ID_CHARACTER, response) as NetworkResponse<ApiError.Response, CharacterDomain>
    }
}

