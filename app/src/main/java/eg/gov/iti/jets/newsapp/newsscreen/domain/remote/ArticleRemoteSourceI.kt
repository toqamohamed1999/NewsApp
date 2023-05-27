package eg.gov.iti.jets.newsapp.newsscreen.domain.remote

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface ArticleRemoteSourceI {

    suspend fun getNews(): Flow<NewsModel>
}