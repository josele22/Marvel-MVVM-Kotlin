package com.josetorres.marvel.domain.model

import com.google.gson.annotations.SerializedName
import com.josetorres.marvel.data.datasources.remote.model.response.*

data class CharacterDomain(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("comics")
    val comics: Comics? = null,
    @SerializedName("series")
    val series: Series? = null,
    @SerializedName("stories")
    val stories: Stories? = null,
    @SerializedName("events")
    val events: Events? = null,
    @SerializedName("urls")
    val urls: List<Url>? = null
)




