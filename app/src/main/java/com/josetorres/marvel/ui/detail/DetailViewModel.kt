package com.josetorres.marvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josetorres.marvel.CharacterApplication
import com.josetorres.marvel.R
import com.josetorres.marvel.data.datasources.remote.CharacterRemoteDataSourceImpl
import com.josetorres.marvel.data.datasources.remote.model.response.NetworkResponse
import com.josetorres.marvel.data.datasources.remote.service.retrofit.RemoteClient
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.model.CharacterDomain
import com.josetorres.marvel.domain.usescases.GetCharacterDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val loadCharacterDetailUseCase: GetCharacterDetailUseCase) : ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state


    fun loadCharacterDetail(idCharacter: String?) {

        viewModelScope.launch(Dispatchers.Main) {

            _progressVisible.value = true

            if (!idCharacter.isNullOrEmpty()) {

                when (val result = loadCharacterDetailUseCase.invoke(idCharacter)) {

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
                    error = CharacterApplication.applicationContext().resources.getString(R.string.api_response_detail_character)
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