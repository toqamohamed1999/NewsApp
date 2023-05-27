package eg.gov.iti.jets.newsapp.newsscreen.domain.model

import android.util.Log
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import eg.gov.iti.jets.newsapp.util.Constants
import java.util.*

@Entity(tableName = Constants.ARTICLE_TABLE)
data class Article(
    @Embedded val source: ArticleSource?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    @PrimaryKey var articleId: Int
)
