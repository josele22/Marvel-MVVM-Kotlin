package com.josetorres.marvel.di

import com.josetorres.marvel.data.datasources.remote.CharacterRemoteDataSourceImpl
import com.josetorres.marvel.data.datasources.remote.service.retrofit.RemoteClient
import com.josetorres.marvel.data.repository.CharacterRepositoryImpl
import com.josetorres.marvel.data.repository.ICharacterRepository
import org.koin.dsl.module


val dataModule = module {

    single {
        RemoteClient()
    }

    single<ICharacterRepository> {
        CharacterRepositoryImpl(remoteDataSource = get())
    }

    single {
        CharacterRemoteDataSourceImpl(remoteClient = get())
    }

}