package com.josetorres.marvel.data.datasources.remote.service

import com.josetorres.marvel.CharacterApplication
import com.josetorres.marvel.R
import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.datasources.remote.model.response.Result
import com.josetorres.marvel.data.toDomain
import com.josetorres.marvel.data.toDomainList

class ApiResponseType() {

    companion object {
        const val ID_CHARACTER = 0
        const val ID_CHARACTER_LIST = 1
    }

    fun getResponseType(
        dataType: Int,
        response: NetworkResponse<ApiError, Result>
    ): NetworkResponse<ApiError.Response, Any> {

        return if (response != null) {

            return when (response) {

                is NetworkResponse.Error -> {

                    return when (response.errorType) {

                        //Cogeremos de la base de datos
                        is ApiError.HttpError -> {
                            NetworkResponse.Error(ApiError.Response(messageError = response.errorType.body))
                        }

                        is ApiError.NetworkError -> {
                            NetworkResponse.Error(ApiError.Response(messageError = response.errorType.throwable.message))
                        }

                        is ApiError.UnknownApiError -> {
                            NetworkResponse.Error(ApiError.Response(messageError = response.errorType.throwable.message))
                        }

                    }
                }
                is NetworkResponse.Success -> {

                    return if (!response.success.data?.characters.isNullOrEmpty()) {

                        return when (dataType) {

                            ID_CHARACTER -> NetworkResponse.Success(
                                response.success.data!!.characters!!.first().toDomain()
                            )

                            ID_CHARACTER_LIST -> NetworkResponse.Success(
                                response.success.data!!.characters!!.toDomainList()
                            )


                            else -> {
                                NetworkResponse.Error(ApiError.Response(messageError = CharacterApplication.applicationContext().resources.getString(R.string.api_response_type_id_type)))
                            }
                        }

                    } else {
                        NetworkResponse.Error(ApiError.Response(CharacterApplication.applicationContext().resources.getString(R.string.api_response_type_data)))
                    }

                }
            }

        } else {
            NetworkResponse.Error(ApiError.Response(CharacterApplication.applicationContext().resources.getString(R.string.api_response_type_response)))
        }

    }
}
