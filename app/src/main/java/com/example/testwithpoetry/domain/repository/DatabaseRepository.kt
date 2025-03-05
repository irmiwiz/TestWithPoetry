package com.example.testwithpoetry.domain.repository

import com.example.testwithpoetry.data.local.database.FavoriteAuthorEntity
import com.example.testwithpoetry.data.local.database.PoetryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    db: PoetryDatabase
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
