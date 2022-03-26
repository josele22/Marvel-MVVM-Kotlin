package com.josetorres.marvel.data.datasources.remote.model.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val characters: List<Character>?,
    @SerializedName("total")
    val total: Int?
)