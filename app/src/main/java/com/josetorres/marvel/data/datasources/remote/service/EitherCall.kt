package com.josetorres.marvel.data.datasources.remote.service

import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

internal class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<NetworkResponse<ApiError, R>> {

    override fun enqueue(callback: Callback<NetworkResponse<ApiError, R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            private fun Response<R>.toEither(): NetworkResponse<ApiError, R> {
                // Http error response (4xx - 5xx)
                if (!isSuccessful) {
                    val errorBody = errorBody()?.string() ?: ""
                    return NetworkResponse.Error(ApiError.HttpError(code(), errorBody))
                }

                // Http success response with body
                body()?.let { body -> return NetworkResponse.Success(body) }

                // if we defined Unit as success type it means we expected no response body
                // e.g. in case of 204 No Content
                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    NetworkResponse.Success(Unit) as NetworkResponse<ApiError, R>
                } else {
                    @Suppress("UNCHECKED_CAST")
                    NetworkResponse.Error(UnknownError("Response body was null")) as NetworkResponse<ApiError, R>
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> ApiError.NetworkError(throwable)
                    else -> ApiError.UnknownApiError(throwable)
                }
                callback.onResponse(this@EitherCall, Response.success(NetworkResponse.Error(error)))
            }
        }
    )

    override fun clone(): Call<NetworkResponse<ApiError, R>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<NetworkResponse<ApiError, R>> {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }

    // override remaining methods - trivial
}