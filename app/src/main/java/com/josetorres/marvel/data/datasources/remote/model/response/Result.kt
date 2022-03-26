package com.josetorres.marvel.data.datasources.remote.model.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("data")
    val data: Data?
)


sealed class NetworkResponse<out L, out R> {

    data class Error<out L>(val errorType: L) : NetworkResponse<L, Nothing>()

    data class Success<out R>(val success: R) : NetworkResponse<Nothing, R>()

}

sealed class ApiError {

    data class HttpError(val code: Int, val body: String) : ApiError()

    data class NetworkError(val throwable: Throwable) : ApiError()

    data class UnknownApiError(val throwable: Throwable) : ApiError()

    data class Response(val code: String? = null, val messageError: String? = null, val status: String? = null)
}






