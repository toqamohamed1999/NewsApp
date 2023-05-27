package eg.gov.iti.jets.newsapp.newsscreen.domain.data

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.ArticleSource
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.NewsAPIInterface

class FackNewsAPIInterface : NewsAPIInterface {

    private val fakeArticlesList = listOf(
        Article(
            source = ArticleSource("1", "CNN"),
            author = "John Doe",
            title = "Breaking News",
            description = "This is a breaking news article.",
            url = "https://www.example.com/ar" +
                    "ticle",
            urlToImage = "https://www.example.com/article/image.jpg",
            publishedAt = "2022-05-27T00:00:00Z",
            content = "This is the content of the breaking news article."
        ),
        Article(
            source = ArticleSource("2", "BBC"),
            author = "Jane Smith",
            title = "World News",
            description = "This is a world news article.",
            url = "https://www.example.com/world-news",
            urlToImage = "https://www.example.com/world-news/image.jpg",
            publishedAt = "2022-05-26T00:00:00Z",
            content = "This is the content of the world news article."
        ))

     val fakeNewsModel = NewsModel(
        status = "ok",
        totalResults = "2",
        articles = fakeArticlesList
    )

    override suspend fun getNews(countryCode: String, apiKey: String): NewsModel {
        return fakeNewsModel
    }


}