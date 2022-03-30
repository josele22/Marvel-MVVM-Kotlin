package com.josetorres.marvel.domain.usescases

import com.josetorres.marvel.data.datasources.remote.EitherCharacter
import com.josetorres.marvel.data.datasources.remote.EitherListCharacter
import com.josetorres.marvel.data.datasources.remote.SucessResponse
import com.josetorres.marvel.data.datasources.remote.characterListFakeDataOne
import com.josetorres.marvel.data.datasources.remote.model.response.Thumbnail
import com.josetorres.marvel.domain.model.CharacterDomain

class FakeCharacterListUseCase(private val repository: RepositoryFakeImpl) {

    suspend fun invoke(): EitherListCharacter {
        return repository.getCharacterList()
    }
}

class FakeCharacterUseCase(private val repository: RepositoryFakeImpl) {

    suspend fun invoke(idCharacter: String): EitherCharacter {
        return repository.getCharacter(idCharacter)
    }
}

class RepositoryFakeImpl() : ICharacterRepositoryFake {
    override suspend fun getCharacterList(): EitherListCharacter {
        return eitherCharacterListOkCaseOne
    }

    override suspend fun getCharacter(id: String): EitherCharacter {
        return eitherCharacterOkCaseOne
    }
}

/**FAKE EITHER REPOSITORY IMPLEMENTATION**/
interface ICharacterRepositoryFake {
    suspend fun getCharacterList(): EitherListCharacter
    suspend fun getCharacter(id: String): EitherCharacter
}

/**RESPONSE OK CHARACTER LIST**/
val eitherCharacterListOkCaseOne =
    EitherListCharacter(success = SucessResponse("200", characterListFakeDataOne()))

/**RESPONSE OK CHARACTER**/
val eitherCharacterOkCaseOne =
    EitherCharacter(success = CharacterDomain(1, name="Spiderman", description="SuperHero", thumbnail = Thumbnail(".jpg", "www.photo2.com")))




