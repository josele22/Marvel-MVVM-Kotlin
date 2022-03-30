package com.josetorres.marvel.di

import com.josetorres.marvel.ui.detail.DetailViewModel
import com.josetorres.marvel.ui.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {

    viewModel {
        CharacterListViewModel(loadCharacterListUseCase = get())
    }

    viewModel {
        DetailViewModel(loadCharacterDetailUseCase = get())
    }

}