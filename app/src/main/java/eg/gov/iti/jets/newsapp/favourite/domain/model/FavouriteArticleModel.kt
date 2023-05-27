package eg.gov.iti.jets.newsapp.favourite.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.ArticleSource
import eg.gov.iti.jets.newsapp.util.Constants


@Entity(tableName = Constants.FAVOURITE_TABLE_NAME)
data class FavouriteArticleModel( @Embedded val source: ArticleSource,
                             val author: String,
                             val title: String,
                             val description: String,
                             val url: String,
                             val urlToImage: String,
                             val publishedAt: String,
                             val content: String,
                             @PrimaryKey val articleId: Int = content.hashCode())

