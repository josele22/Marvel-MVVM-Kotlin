package com.josetorres.marvel.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josetorres.marvel.data.datasources.remote.model.response.Thumbnail
import com.josetorres.marvel.domain.model.CharacterDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FakeViewModel(
    private val fakeListCharacterUseCase: FakeListCharacterUseCase,
    private val fakeCharacterUseCase: FakeCharacterUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterDomain>>()

    val characters: LiveData<List<CharacterDomain>> get() = _characters

    fun onLoadCharacterList() {

        viewModelScope.launch(Dispatchers.Main) {
            _characters.value = fakeListCharacterUseCase.invoke()
        }

    }

    fun onLoadCharacter(idCharacter: String?) {

        viewModelScope.launch(Dispatchers.Main) {
            _characters.value = fakeCharacterUseCase.invoke(idCharacter)
        }

    }


    class FakeListCharacterUseCase {

        suspend fun invoke(): List<CharacterDomain> =
            withContext(Dispatchers.IO) {
                delay(2000)
                characterListFakeData()
            }
    }

    class FakeCharacterUseCase {

        suspend fun invoke(idCharacter: String?): List<CharacterDomain> =
            withContext(Dispatchers.IO) {
                characterFakeData(idCharacter)
            }
    }
}


fun characterListFakeData(): List<CharacterDomain> {
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

fun characterFakeData(idCharacter: String?): List<CharacterDomain> {
    return listOf(
        CharacterDomain(
            id = idCharacter?.toInt(),
            name = "Name 1",
            description = "Hero 1 strong",
            thumbnail = Thumbnail(".jpg", "www.photo1.com")
        )
    )
}






