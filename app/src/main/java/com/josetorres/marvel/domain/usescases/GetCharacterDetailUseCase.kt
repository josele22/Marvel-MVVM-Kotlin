package com.josetorres.marvel.domain.usescases

import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.model.CharacterDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterDetailUseCase(private val repository: CharacterRepositoryImpl) {

    suspend fun invoke(idCharacter: String): NetworkResponse<ApiError.Response, CharacterDomain>? =
        withContext(Dispatchers.IO) {
            repository.getCharacterDetail(idCharacter)
        }
}