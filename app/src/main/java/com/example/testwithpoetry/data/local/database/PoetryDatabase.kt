package com.example.testwithpoetry.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val POETRY_DATABASE = "POETRY_DATABASE"
@Database(entities = [FavoriteAuthorEntity::class], version = 1)
abstract class PoetryDatabase : RoomDatabase() {
    abstract fun favoriteAuthorDao(): FavoriteAuthorDao
}