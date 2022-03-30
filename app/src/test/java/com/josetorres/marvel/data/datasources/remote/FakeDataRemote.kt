package com.josetorres.marvel.data.datasources.remote

import com.josetorres.marvel.data.datasources.remote.model.response.Thumbnail
import com.josetorres.marvel.domain.model.CharacterDomain
import retrofit2.http.GET
import retrofit2.http.Path

class FakeRemoteDataSource(private val remoteClient: ApiFake) :
    ICharacterRemoteDataSourceFake {

    override suspend fun getCharacterList(): EitherListCharacter {
        return remoteClient.getCharactersListFakeApi()
    }

    override suspend fun getCharacter(id: String): EitherCharacter {
        return remoteClient.getCharacterFakeApi(id)
    }
}

/**FAKE EITHER**/
interface ICharacterRemoteDataSourceFake {
    suspend fun getCharacterList(): EitherListCharacter
    suspend fun getCharacter(id: String): EitherCharacter
}

/**RESPONSE TYPE**/
data class EitherListCharacter(
    val error: ApiError? = null,
    val success: SucessResponse? = null
)

data class EitherCharacter(val error: ApiError? = null, val success: CharacterDomain)

/**Success RESPONSE**/
data class SucessResponse(
    val code: String, val listCharacter: List<CharacterDomain>? = null
)

/***API Error Response***/
data class ApiError(val code: String, val message: String)


/**INTERFAZ API**/

interface ApiFake {

    @GET("v1/public/characters")
    suspend fun getCharactersListFakeApi() = EitherListCharacter()

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterFakeApi(@Path("characterId") characterId: String): EitherCharacter

}

/**RESPONSE KO CHARACTER LIST**/
val eitherCharacterListKoCaseOne = EitherListCharacter(ApiError("409", "Empty parameter"))

val eitherCharacterListKoCaseTwo = EitherListCharacter(ApiError("404", "Not found character list"))

val eitherCharacterListOkCaseOne =
    EitherListCharacter(success = SucessResponse("200", characterListFakeDataOne()))

/**RESPONSE LIST CHARACTERS**/
fun characterListFakeDataOne(): List<CharacterDomain> {
    return listOf(
        CharacterDomain(
            name = "Name 1",
            description = "Hero 1 strong",
            thumbnail = Thumbnail(".jpg", "www.photo1.com")
        ),
        CharacterDomain(
            name = "Name 2",
            description = "Hero 2 power",
            thumbnail = Thumbnail(".jpg", "www.photo2.com")
        ),
        CharacterDomain(
            name = "Name 3",
            description = "Hero 3 weak ",
            thumbnail = Thumbnail(".jpg", "www.photo3.com")
        )

    )
}

fun characterListFakeDataTwo(): List<CharacterDomain> {
    return listOf(
        CharacterDomain(
            name = "Name 1",
            description = "Hero 1 strong",
            thumbnail = Thumbnail(".jpg", "www.photo1.com")
        ),
        CharacterDomain(
            name = "Name 2",
            description = "Hero 2 power",
            thumbnail = Thumbnail(".jpg", "www.photo2.com")
        ),
        CharacterDomain(
            name = "Name 3",
            description = "Hero 3 weak ",
            thumbnail = Thumbnail(".jpg", "www.photo3.com")
        )

    )
}





