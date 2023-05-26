package eg.gov.iti.jets.newsapp.newsscreen.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import eg.gov.iti.jets.newsapp.util.ARTICLE_TABLE

@Entity(tableName = ARTICLE_TABLE)
data class Article(val source: ArticleSource, val author : String, val title : String,
                   val description : String, val url : String, val urlToImage : String,
                   val publishedAt : String, val content : String,
                   @PrimaryKey val id: Int = content.hashCode())
