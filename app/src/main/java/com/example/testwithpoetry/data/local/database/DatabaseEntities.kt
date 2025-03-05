package com.example.testwithpoetry.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITE_ENTITY_NAME = "fav_authors"
@Entity(tableName = FAVORITE_ENTITY_NAME)
data class FavoriteAuthorEntity(
    @PrimaryKey val name: String
)