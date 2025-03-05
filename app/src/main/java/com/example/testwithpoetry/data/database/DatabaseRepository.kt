package com.example.testwithpoetry.data.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val db: PoetryDatabase
) {
    private val favoriteAuthorDao = db.favoriteAuthorDao()

    suspend fun addFavorite(author: String) {
        withContext(Dispatchers.IO) {
            favoriteAuthorDao.insertFavoriteAuthor(FavoriteAuthorEntity(name = author))
        }
    }

    suspend fun getFavorites(): List<FavoriteAuthorEntity> {
        return withContext(Dispatchers.IO) {
            favoriteAuthorDao.getAllFavoritesAuthors()
        }
    }
}
