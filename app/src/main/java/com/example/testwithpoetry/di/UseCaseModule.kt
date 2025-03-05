package com.example.testwithpoetry.di

import com.example.testwithpoetry.domain.usecase.GetAuthorPoemsUseCase
import com.example.testwithpoetry.domain.usecase.GetAuthorPoemsUseCaseImpl
import com.example.testwithpoetry.domain.usecase.GetAuthorsUseCase
import com.example.testwithpoetry.domain.usecase.GetAuthorsUseCaseImpl
import com.example.testwithpoetry.domain.usecase.GetPoemDetailUseCase
import com.example.testwithpoetry.domain.usecase.GetPoemDetailUseCaseImpl
import com.example.testwithpoetry.domain.usecase.GetUserUseCase
import com.example.testwithpoetry.domain.usecase.GetUserUseCaseImpl
import com.example.testwithpoetry.domain.usecase.SaveFavoriteAuthorUseCase
import com.example.testwithpoetry.domain.usecase.SaveFavoriteAuthorUseCaseImpl
import com.example.testwithpoetry.domain.usecase.SaveUserUseCase
import com.example.testwithpoetry.domain.usecase.SaveUserUseCaseImpl
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

    @Binds
    @Singleton
    abstract fun bindSaveFavoriteAuthorUseCase(
        saveFavoriteAuthorUseCase: SaveFavoriteAuthorUseCaseImpl
    ): SaveFavoriteAuthorUseCase


    @Binds
    @Singleton
    abstract fun bindGetAuthorPoemsUseCase(
        getAuthorPoemsUseCase: GetAuthorPoemsUseCaseImpl
    ): GetAuthorPoemsUseCase

    @Binds
    @Singleton
    abstract fun bindGetPoemDetailUseCase(
        getPoemDetailUseCase: GetPoemDetailUseCaseImpl
    ): GetPoemDetailUseCase

    @Binds
    @Singleton
    abstract fun bindGetUserUseCase(
        getUserUseCase: GetUserUseCaseImpl
    ): GetUserUseCase

    @Binds
    @Singleton
    abstract fun bindSaveUserUseCase(
        saveUserUseCase: SaveUserUseCaseImpl
    ): SaveUserUseCase

}