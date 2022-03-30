package com.josetorres.marvel.domain.usescases

import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.model.CharacterDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharactersListUseCase(
    private val repository: CharacterRepositoryImpl
) {
    suspend fun invoke(): NetworkResponse<ApiError.Response, List<CharacterDomain>> =
        withContext(Dispatchers.IO) {
            repository.getCharactersList()
        }
}