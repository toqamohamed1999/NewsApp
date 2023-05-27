package eg.gov.iti.jets.newsapp.favourite.data.local

import eg.gov.iti.jets.newsapp.base.local.db.AppDataBase
import eg.gov.iti.jets.newsapp.favourite.domain.local.FavLocalSourceInterface
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import kotlinx.coroutines.flow.Flow


class FavLocalSourceImp private constructor(): FavLocalSourceInterface {
    companion object {
        @Volatile
        private var favLocalSourceInstance : FavLocalSourceInterface?= null

        fun getFavLocalSourceInstance() = favLocalSourceInstance ?: synchronized(this){
            val temp = FavLocalSourceImp()
            favLocalSourceInstance = temp
            temp
        }
    }

    override suspend fun getAllFavourite(): Flow<List<FavouriteArticleModel>> {
        return  AppDataBase.getInstance().getFavouriteDao().getAllFavouritePlaces()
    }

    override suspend fun insertFavouriteArticle(favArticle: FavouriteArticleModel) {
        return AppDataBase.getInstance().getFavouriteDao().insertFavouriteArticle(favArticle)
    }
}