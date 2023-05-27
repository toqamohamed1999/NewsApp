package eg.gov.iti.jets.newsapp.favourite.domain.local

import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import kotlinx.coroutines.flow.Flow

interface FavLocalSourceInterface {
    suspend fun getAllFavourite() : Flow<List<FavouriteArticleModel>>
    suspend fun insertFavouriteArticle(favArticle : FavouriteArticleModel)
    suspend fun  deleteFromFavorites(id:Int)
    suspend fun isFavorite(id:Int):Int
}