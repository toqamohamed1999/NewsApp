package eg.gov.iti.jets.newsapp.newsscreen.data.remote

import eg.gov.iti.jets.newsapp.base.remote.APIClient
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.ArticleRemoteSourceI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ArticleRemoteSource : ArticleRemoteSourceI{

    override suspend fun getNews(): Flow<NewsModel> {
        return flowOf(APIClient.newsAPIInterface.getNews())
    }
}