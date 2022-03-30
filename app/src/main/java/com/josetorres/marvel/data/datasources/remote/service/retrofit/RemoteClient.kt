package com.josetorres.marvel.data.datasources.remote.service.retrofit

import com.josetorres.marvel.data.datasources.remote.service.EitherCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteClient {

    val BASE_URL = "https://gateway.marvel.com/"
    val TS = "1000"
    val PUBLIC_KEY = "1e280d0ca9f8a5257cd14ecd08c40bd8"
    val HASH = "4f1829f75efbb01a8ea068f0d419a653"//it is generated outside the app for security

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory())
            .build()
    }

    val serviceCharacters: MarvelApi = retrofit().create(MarvelApi::class.java)

    private fun okHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", TS)
                .addQueryParameter("apikey", PUBLIC_KEY)
                .addQueryParameter("hash", HASH) //it's generate with public and private key
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClient.build()
    }
}