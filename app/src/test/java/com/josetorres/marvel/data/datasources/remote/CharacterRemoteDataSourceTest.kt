package com.josetorres.marvel.data.datasources.remote

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
class CharacterRemoteDataSourceTest {

    @Mock
    lateinit var remoteClientFake: ApiFake

    @Mock
    lateinit var remoteDataSourceFakeImpl: FakeRemoteDataSource

    @Test
    fun `server return code 200 - case 0`() {

        runBlockingTest {

            whenever(remoteDataSourceFakeImpl.getCharacterList()).thenReturn(
                eitherCharacterListOkCaseOne
            ).let {
                whenever(remoteClientFake.getCharactersListFakeApi()).thenReturn(
                    eitherCharacterListOkCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherListCharacter( success = SucessResponse("200",characterListFakeDataTwo()))

            //Check in RemoteDataSource
            Assert.assertEquals(expectedResponse, remoteDataSourceFakeImpl.getCharacterList())

            //Check in RetrofitClient
            Assert.assertEquals(expectedResponse, remoteClientFake.getCharactersListFakeApi())
        }
    }

    @Test
    fun `server return code 409 - case 1`() {

        runBlockingTest {

            whenever(remoteDataSourceFakeImpl.getCharacterList()).thenReturn(
                eitherCharacterListKoCaseOne
            ).let {
                whenever(remoteClientFake.getCharactersListFakeApi()).thenReturn(
                    eitherCharacterListKoCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherListCharacter(ApiError("409", "Empty parameter"))

            //Check in RemoteDataSource
            Assert.assertEquals(expectedResponse, remoteDataSourceFakeImpl.getCharacterList())

            //Check in RetrofitClient
            Assert.assertEquals(expectedResponse, remoteClientFake.getCharactersListFakeApi())
        }
    }

    @Test
    fun `server return code 404 - case 2`() {

        runBlockingTest {

            whenever(remoteDataSourceFakeImpl.getCharacterList()).thenReturn(
                eitherCharacterListKoCaseTwo
            ).let {
                whenever(remoteClientFake.getCharactersListFakeApi()).thenReturn(
                    eitherCharacterListKoCaseTwo
                )
            }

            //Expected response
            val expectedResponse = EitherListCharacter(ApiError("404", "Not found character list"))

            //Check in RemoteDataSource
            Assert.assertEquals(expectedResponse, remoteDataSourceFakeImpl.getCharacterList())

            //Check in RetrofitClient
            Assert.assertEquals(expectedResponse, remoteClientFake.getCharactersListFakeApi())
        }
    }
}