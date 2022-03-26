package com.josetorres.marvel.data.datasources.remote.model.response


import com.google.gson.annotations.SerializedName

data class ItemEvents(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
)