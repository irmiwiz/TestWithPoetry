package com.example.testwithpoetry.di

import com.example.testwithpoetry.GetAuthorsUseCase
import com.example.testwithpoetry.GetAuthorsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetAuthorsUseCase(
        getAuthorsUseCase: GetAuthorsUseCaseImpl
    ): GetAuthorsUseCase
}