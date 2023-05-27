package eg.gov.iti.jets.newsapp.favourite.data.repo

import eg.gov.iti.jets.newsapp.favourite.data.local.FavLocalSourceImp
import eg.gov.iti.jets.newsapp.favourite.domain.local.FavLocalSourceInterface
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import eg.gov.iti.jets.newsapp.favourite.domain.repo.FavRepoInterface
import kotlinx.coroutines.flow.Flow

class FavouriteRepoImp private constructor(val favLocalSourceInterface: FavLocalSourceInterface) :
    FavRepoInterface {

    companion object {
        @Volatile
        private var favRepo: FavouriteRepoImp? = null

        fun getFavRepoInstance(
            favLocalSource: FavLocalSourceInterface
        ) = favRepo ?: synchronized(this) {
            val temp = FavouriteRepoImp(favLocalSource)
            this.favRepo = temp
            temp
        }
    }


    override suspend fun insertArticleToFav(favArticle: FavouriteArticleModel) {
        return favLocalSourceInterface.insertFavouriteArticle(favArticle)
    }

    override suspend fun getAllFavourite(): Flow<List<FavouriteArticleModel>> {
        return favLocalSourceInterface.getAllFavourite()
    }
}