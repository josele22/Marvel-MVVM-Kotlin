package com.josetorres.marvel.data.repository

import com.josetorres.marvel.data.datasources.remote.CharacterRemoteDataSourceImpl
import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.datasources.remote.service.retrofit.RemoteClient
import com.josetorres.marvel.domain.model.CharacterDomain

class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSourceImpl = CharacterRemoteDataSourceImpl(
        RemoteClient
    )
) : ICharacterRepository {

    override suspend fun getCharactersList(): NetworkResponse<ApiError.Response, List<CharacterDomain>> {
        return remoteDataSource.getCharactersListSource()
    }

    override suspend fun getCharacterDetail(idCharacter: String): NetworkResponse<ApiError.Response, CharacterDomain> {
        return remoteDataSource.getCharacterDetailSource(idCharacter)
    }
}





























