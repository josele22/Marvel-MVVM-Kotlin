package com.josetorres.marvel.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.josetorres.marvel.data.datasources.remote.model.response.Thumbnail
import com.josetorres.marvel.domain.model.CharacterDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var observerOne: Observer<List<CharacterDomain>>

    @Mock
    lateinit var observerTwo: Observer<List<CharacterDomain>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var fakeListCharacterUseCase: FakeViewModel.FakeListCharacterUseCase

    @Mock
    lateinit var fakeCharacterUseCase: FakeViewModel.FakeCharacterUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `onLoad all characters`() {

        runBlockingTest {

            val fakeList = listOf(
                CharacterDomain(
                    name = "Spiderman",
                    description = "Spiderman is a hero....",
                    thumbnail = Thumbnail("", "")
                ),
                CharacterDomain(
                    name = "Superman",
                    description = "Superman is a hero....",
                    thumbnail = Thumbnail("", "")
                )
            )

            whenever(fakeListCharacterUseCase.invoke()).thenReturn(fakeList)

            val vm = FakeViewModel(fakeListCharacterUseCase,fakeCharacterUseCase)

            vm.characters.observeForever(observerOne)

            vm.onLoadCharacterList()

            verify(observerOne).onChanged(fakeList)

        }
    }

    @Test
    fun `onLoad character`() {

        runBlockingTest {

            val fakeList = listOf(
                CharacterDomain(
                    name = "Batman",
                    description = "Batman is a hero....",
                    thumbnail = Thumbnail("", "")
                )
            )

            whenever(fakeCharacterUseCase.invoke("1234")).thenReturn(fakeList)

            val vm = FakeViewModel(fakeListCharacterUseCase,fakeCharacterUseCase)

            vm.characters.observeForever(observerTwo)

            vm.onLoadCharacter("1234")

            verify(observerTwo).onChanged(fakeList)

        }
    }

}