package com.josetorres.marvel.data.repository

import com.josetorres.marvel.data.datasources.remote.model.response.*
import com.josetorres.marvel.domain.model.CharacterDomain

interface ICharacterRepository {

    suspend fun getCharactersList(): NetworkResponse<ApiError.Response, List<CharacterDomain>>

    suspend fun getCharacterDetail(idCharacter: String): NetworkResponse<ApiError.Response, CharacterDomain>
}