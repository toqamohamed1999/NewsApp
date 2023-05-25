package eg.gov.iti.jets.newsapp.newsscreen.domain.model

data class Article(val source: ArticleSource, val author : String, val title : String,
                   val description : String, val url : String, val urlToImage : String,
                   val publishedAt : String, val content : String)
