package eg.gov.iti.jets.newsapp.newsscreen.data.repo

import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleLocalSource
import eg.gov.iti.jets.newsapp.newsscreen.domain.local.ArticleLocalSourceI
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.ArticleRemoteSourceI
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.NewsAPIInterface
import eg.gov.iti.jets.newsapp.newsscreen.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow

class RepoImpl private constructor(
    private var remoteSource: ArticleRemoteSourceI,
    private var localeSource: ArticleLocalSourceI
) :  NewsRepo{

    companion object {
        private var instance: NewsRepo? = null

        fun getInstance(
            remoteSource: ArticleRemoteSourceI,
            localeSource: ArticleLocalSourceI
        ): NewsRepo? {
            return instance ?: synchronized(this) {
                instance = RepoImpl(remoteSource, localeSource)

                instance
            }

        }
    }

    override suspend fun getNews(country : String): Flow<NewsModel> {
        return remoteSource.getNews()
    }

    override suspend fun getStoredNews() : Flow<List<Article>> {
        return localeSource.getStoredArticles()
    }

    override suspend fun deleteAllArticles() {
        localeSource.deleteAllArticles()
    }

}