package eg.gov.iti.jets.newsapp.newsscreen.data.local

import android.content.Context
import eg.gov.iti.jets.newsapp.base.local.db.AppDataBase
import eg.gov.iti.jets.newsapp.newsscreen.domain.local.ArticleLocalSourceI
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import kotlinx.coroutines.flow.Flow

class ArticleLocalSource (context: Context) : ArticleLocalSourceI {

    private val articleDao : ArticleDao by lazy{
        AppDataBase.getInstance(context)
            .getArticleDao()
    }

    override fun getStoredArticles(): Flow<List<Article>> {
         return articleDao.getStoredArticles()
    }

    override suspend fun insertArticles(articles: List<Article>):  List<Long> {
        return articleDao.insertArticles(articles)
    }

    override suspend fun deleteArticle(article: Article): Int {
        return articleDao.deleteArticle(article)
    }

    override suspend fun deleteAllArticles() {
        articleDao.deleteAllArticles()
    }
}