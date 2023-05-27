package eg.gov.iti.jets.newsapp.favourite.domain.repo

import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel

interface FavRepoInterface {

    suspend fun insertArticleToFav(favArticle: FavouriteArticleModel)
}