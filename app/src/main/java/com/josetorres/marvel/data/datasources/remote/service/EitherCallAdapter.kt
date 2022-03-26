package com.josetorres.marvel.data.datasources.remote.service

import com.josetorres.marvel.data.datasources.remote.model.response.ApiError
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<NetworkResponse<ApiError, R>>> {

    override fun adapt(call: Call<R>): Call<NetworkResponse<ApiError, R>> = EitherCall(call, successType)

    override fun responseType(): Type = successType
}