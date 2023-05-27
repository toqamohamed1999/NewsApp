package eg.gov.iti.jets.newsapp.newsscreen.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import eg.gov.iti.jets.newsapp.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.ARTICLE_TABLE)
data class Article(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    @PrimaryKey var articleId: Int
) :Parcelable
fun Article.convertToFavorite():FavouriteArticleModel{
    return FavouriteArticleModel(author?:"",title,description?:"",url?:"",urlToImage?:"",publishedAt,content?:"",articleId)
}