package com.sample.compose.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBreedRepository(
        breedRepositoryImpl: BreedRepositoryImpl
    ): BreedRepository

    @Binds
    @Singleton
    abstract fun bindFactRepository(
        factRepositoryImpl: FactRepositoryImpl
    ): FactRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}
