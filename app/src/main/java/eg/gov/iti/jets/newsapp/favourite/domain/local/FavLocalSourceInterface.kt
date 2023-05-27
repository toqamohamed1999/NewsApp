package eg.gov.iti.jets.newsapp.favourite.domain.local

import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel

interface FavLocalSourceInterface {
    suspend fun insertFavouriteArticle(favArticle : FavouriteArticleModel)
}