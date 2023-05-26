package eg.gov.iti.jets.newsapp.newsscreen.domain.data

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.NewsAPIInterface
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.NewsRepo

class RepoImpl (private val newsAPIInterface : NewsAPIInterface) : NewsRepo {
    override suspend fun getNews(country: String): NewsModel {
        return newsAPIInterface.getNews(country)
    }

}