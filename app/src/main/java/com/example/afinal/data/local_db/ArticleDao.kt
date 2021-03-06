package com.example.afinal.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.afinal.data.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles : List<Article>)

    @Query("SELECT * FROM articles ORDER BY id DESC")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("SELECT DISTINCT * FROM articles WHERE title LIKE '%' || :search || '%' ")
    fun getSearchArticles(search : String): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    fun getAllFavorites(): LiveData<List<Article>>
}
