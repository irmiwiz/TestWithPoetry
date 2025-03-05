package com.example.testwithpoetry.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteAuthorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAuthor(author: FavoriteAuthorEntity)

    @Query("SELECT * FROM $FAVORITE_ENTITY_NAME")
    suspend fun getAllFavoritesAuthors(): List<FavoriteAuthorEntity>
}