package eg.gov.iti.jets.newsapp.newsscreen.domain.repo

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun getNews (country : String = "US") : Flow<List<Article>>
    suspend fun insertArticles(articles : List<Article>):  List<Long>
    suspend fun deleteAllArticles()
}