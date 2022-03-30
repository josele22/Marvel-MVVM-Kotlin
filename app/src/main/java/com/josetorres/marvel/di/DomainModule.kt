package com.josetorres.marvel.di

import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.domain.usescases.GetCharacterDetailUseCase
import com.josetorres.marvel.domain.usescases.GetCharactersListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetCharactersListUseCase(CharacterRepositoryImpl(remoteDataSource = get()))
    }

    factory {
        GetCharacterDetailUseCase(CharacterRepositoryImpl(remoteDataSource = get()))
    }

}