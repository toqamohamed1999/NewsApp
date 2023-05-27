package eg.gov.iti.jets.newsapp.favourite.domain.repo

import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import kotlinx.coroutines.flow.Flow

interface FavRepoInterface {

    suspend fun insertArticleToFav(favArticle: FavouriteArticleModel)
    suspend fun getAllFavourite() : Flow<List<FavouriteArticleModel>>
}