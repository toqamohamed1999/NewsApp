package eg.gov.iti.jets.newsapp.newsdetails.data.repo

import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import eg.gov.iti.jets.newsapp.favourite.domain.repo.FavRepoInterface
import eg.gov.iti.jets.newsapp.newsdetails.domain.repo.NewsDetailsRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull

class NewsDetailsNewsRepoImpl(private val favRepoInterface: FavRepoInterface):NewsDetailsRepo {
    override suspend fun addToFavoriteNews(article: FavouriteArticleModel): Boolean {
        try {
            favRepoInterface.insertArticleToFav(article)
        }catch (e:java.lang.Exception){
            return false
        }
        return  true
    }

    override suspend fun deleteFromFavorite(articleId: Int): Boolean {
        try {
            favRepoInterface.deleteFromFavorites(articleId)
        }catch (e:java.lang.Exception){
            return false
        }
        return  true
    }

    override suspend fun isFavorite(articleId: Int): Boolean {
        val res = favRepoInterface.isFavorite(articleId)
       return if(res>0)
        {
           return true
        }else{
            false
        }
    }
}