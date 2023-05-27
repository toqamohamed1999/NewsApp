package eg.gov.iti.jets.newsapp.newsdetails.domain.repo

import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel


interface NewsDetailsRepo {

   suspend fun addToFavoriteNews(article: FavouriteArticleModel):Boolean
    suspend fun deleteFromFavorite(articleId:Int):Boolean
    suspend fun isFavorite(articleId: Int):Boolean
}