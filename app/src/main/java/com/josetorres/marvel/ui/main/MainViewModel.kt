package com.josetorres.marvel.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josetorres.marvel.data.datasources.remote.CharacterRemoteDataSourceImpl
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.datasources.remote.service.retrofit.RemoteClient
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.model.CharacterDomain
import com.josetorres.marvel.domain.usescases.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel() : ViewModel() {

    private val loadCharacters = GetCharactersListUseCase(
        CharacterRepositoryImpl(
            CharacterRemoteDataSourceImpl(
                RemoteClient
            )
        )
    )

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state

    fun loadCharacters() {

        viewModelScope.launch(Dispatchers.Main) {

            _progressVisible.value = true

            when (val result = loadCharacters.invoke()) {

                is NetworkResponse.Error -> {

                    _state.value = UiState(
                        error = result.errorType.messageError
                    )
                }

                is NetworkResponse.Success -> {
                    _state.value = UiState(
                        list = result.success
                    )
                }
            }

            _progressVisible.value = false

        }

    }

    data class UiState(
        val list: List<CharacterDomain>? = null,
        val error: String? = null,
    )
}