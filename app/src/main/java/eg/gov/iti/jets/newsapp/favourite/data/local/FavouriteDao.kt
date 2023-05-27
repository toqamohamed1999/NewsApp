package eg.gov.iti.jets.newsapp.favourite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import eg.gov.iti.jets.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteArticle(favArticle : FavouriteArticleModel)

    @Query("select * from ${Constants.FAVOURITE_TABLE_NAME}")
    fun getAllFavouritePlaces(): Flow<List<FavouriteArticleModel>>
    @Query("select count(*) from ${Constants.FAVOURITE_TABLE_NAME} where articleId like :id")
    fun isFavorite(id:Int):Int

    @Query("DELETE FROM ${Constants.FAVOURITE_TABLE_NAME} WHERE articleId = :id")
    fun deleteFromFavorites(id:Int)
}