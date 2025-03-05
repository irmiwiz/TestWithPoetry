package com.example.testwithpoetry.di

import android.content.Context
import androidx.room.Room
import com.example.testwithpoetry.data.local.database.FavoriteAuthorDao
import com.example.testwithpoetry.data.local.database.POETRY_DATABASE
import com.example.testwithpoetry.data.local.database.PoetryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PoetryDatabase {
        return Room.databaseBuilder(
            context,
            PoetryDatabase::class.java,
            POETRY_DATABASE
        ).build()
    }

    @Provides
    fun provideAuthorDao(database: PoetryDatabase): FavoriteAuthorDao {
        return database.favoriteAuthorDao()
    }
}