package eg.gov.iti.jets.newsapp.favourite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteArticle(favArticle : FavouriteArticleModel)
}