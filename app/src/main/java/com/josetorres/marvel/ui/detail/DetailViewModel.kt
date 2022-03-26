package com.josetorres.marvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josetorres.marvel.data.datasources.remote.CharacterRemoteDataSourceImpl
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.datasources.remote.service.retrofit.RemoteClient
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.model.CharacterDomain
import com.josetorres.marvel.domain.usescases.GetCharacterDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val loadCharacterDetail = GetCharacterDetailUseCase(
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


    fun loadCharacterDetail(idCharacter: String?) {

        viewModelScope.launch(Dispatchers.Main) {

            _progressVisible.value = true

            if (!idCharacter.isNullOrEmpty()) {

                when (val result = loadCharacterDetail.invoke(idCharacter)) {

                    is NetworkResponse.Error -> {

                        _state.value = UiState(
                            error = result.errorType.messageError
                        )
                    }

                    is NetworkResponse.Success -> {
                        _state.value = UiState(
                            character = result.success
                        )
                    }
                }
            } else {
                _state.value = UiState(
                    error = "El ID seleccionado no es correcto"
                )
            }

            _progressVisible.value = false


        }
    }

    data class UiState(
        val character: CharacterDomain? = null,
        val error: String? = null,
    )
}