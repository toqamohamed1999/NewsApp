package eg.gov.iti.jets.newsapp.newsscreen.data.local

import androidx.room.*
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("select * from ${Constants.ARTICLE_TABLE}")
    fun getStoredArticles (): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article : Article): Long

    @Delete
    suspend fun deleteArticle(article : Article): Int

    @Query("DELETE FROM ${Constants.ARTICLE_TABLE}")
    suspend fun deleteAllArticles()

}