package eg.gov.iti.jets.newsapp.newsscreen.data.repo

import android.util.Log
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleLocalSource
import eg.gov.iti.jets.newsapp.newsscreen.data.model.NewsResultState
import eg.gov.iti.jets.newsapp.newsscreen.domain.local.ArticleLocalSourceI
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.ArticleRemoteSourceI
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.NewsAPIInterface
import eg.gov.iti.jets.newsapp.newsscreen.domain.repo.NewsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class RepoImpl private constructor(
    private var remoteSource: ArticleRemoteSourceI,
    private var localeSource: ArticleLocalSourceI
) :  NewsRepo{

    private val TAG = "RepoImpl"

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

    override suspend fun getNews(country : String): Flow<List<Article>> {
        var articles : Flow<List<Article>> = flowOf()
            try {
                remoteSource.getNews().let {
                    deleteAllArticles()
                    insertArticles(giveArticleId(it))
                    articles = flowOf(it)
                }
            } catch (e: java.lang.Exception) {
                Log.i(TAG, "getNews: ${e.message}")
                articles = localeSource.getStoredArticles()
            }
        return articles
    }

    override suspend fun insertArticles(articles: List<Article>):  List<Long> {
        return localeSource.insertArticles(articles)
    }

    override suspend fun deleteAllArticles() {
        localeSource.deleteAllArticles()
    }

    private fun giveArticleId(articles : List<Article>) : List<Article>{
        for (article in articles) {
            article.apply {
                articleId = publishedAt.hashCode() + title.hashCode()
            }
        }
        return articles
    }

}