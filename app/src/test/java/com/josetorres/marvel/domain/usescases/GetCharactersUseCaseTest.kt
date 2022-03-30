package com.josetorres.marvel.domain.usescases

import com.josetorres.marvel.data.datasources.remote.*
import com.josetorres.marvel.data.datasources.remote.model.response.Thumbnail
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.model.CharacterDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseTest {

    @Mock
    lateinit var repositoryFake: RepositoryFakeImpl

    @Mock
    lateinit var getCharacterDetailUseCase: FakeCharacterUseCase

    @Mock
    lateinit var getCharactersListUseCase: FakeCharacterListUseCase

    @Test
    fun `server return 200 code - GetListCharacterUseCase`() {

        runBlockingTest {

            whenever(getCharactersListUseCase.invoke()).thenReturn(
                eitherCharacterListOkCaseOne
            ).let {
                whenever(repositoryFake.getCharacterList()).thenReturn(
                    eitherCharacterListOkCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherListCharacter(
                success = SucessResponse(
                    "200",
                    characterListFakeDataTwo()
                )
            )

            //Check in GetCharacrerListUseCase
            Assert.assertEquals(expectedResponse, getCharactersListUseCase.invoke())

            //Check in
            Assert.assertEquals(expectedResponse, repositoryFake.getCharacterList())
        }
    }

    @Test
    fun `server return 200 code - GetCharacterDetailUseCase`() {

        runBlockingTest {

            whenever(getCharacterDetailUseCase.invoke("1234")).thenReturn(
                eitherCharacterOkCaseOne
            ).let {
                whenever(repositoryFake.getCharacter("1234")).thenReturn(
                    eitherCharacterOkCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherCharacter(
                success = CharacterDomain(
                    1,
                    name = "Spiderman",
                    description = "SuperHero",
                    thumbnail = Thumbnail(".jpg", "www.photo2.com")
                )
            )


            //Check in GetCharacrerListUseCase
            Assert.assertEquals(expectedResponse, getCharacterDetailUseCase.invoke("1234"))

            //Check in
            Assert.assertEquals(expectedResponse, repositoryFake.getCharacter("1234"))
        }
    }

}