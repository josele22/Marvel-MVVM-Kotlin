package com.josetorres.marvel.data

import com.josetorres.marvel.data.datasources.remote.model.response.Character
import com.josetorres.marvel.data.datasources.remote.model.response.Thumbnail
import com.josetorres.marvel.domain.model.CharacterDomain

fun Character.toDomain(): CharacterDomain {
    return CharacterDomain(
        id = id.toDefaultInteger(),
        name = name.toEmptyString(),
        description = description.toEmptyString(),
        thumbnail = thumbnail.toEmptyObject(),
    )
}

fun List<Character>.toDomainList(): List<CharacterDomain> {
    return map {
        CharacterDomain(
            id = it.id.toDefaultInteger(),
            name = it.name.toEmptyString(),
            description = it.description.toEmptyString(),
            thumbnail = it.thumbnail.toEmptyObject(),
        )
    }
}

fun String?.toEmptyString(): String {
    if (this == null) {
        return ""
    }
    return toString()
}

fun Thumbnail?.toEmptyObject(): Thumbnail {
    if (this == null) return Thumbnail("", "")
    return this
}

fun Int?.toDefaultInteger(): Int {
    if (this == null) return 0
    return this
}










