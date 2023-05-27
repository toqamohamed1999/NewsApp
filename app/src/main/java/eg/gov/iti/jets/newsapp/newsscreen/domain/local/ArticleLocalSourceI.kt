package eg.gov.iti.jets.newsapp.newsscreen.domain.local


import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleLocalSourceI {

    fun getStoredArticles (): Flow<List<Article>>

    suspend fun insertArticles(articles : List<Article>): List<Long>

    suspend fun deleteArticle(article : Article): Int

    suspend fun deleteAllArticles()

}