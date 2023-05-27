package eg.gov.iti.jets.newsapp.newsscreen.domain.repo

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun getNews (country : String = "US") : Flow<NewsModel>
    suspend fun getStoredNews() : Flow<List<Article>>
    suspend fun deleteAllArticles()
}