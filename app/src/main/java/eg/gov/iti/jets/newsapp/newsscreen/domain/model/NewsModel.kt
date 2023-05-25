package eg.gov.iti.jets.newsapp.newsscreen.domain.model

data class NewsModel(val status : String, val totalResults : String, val articles : List<Article>) {
}

