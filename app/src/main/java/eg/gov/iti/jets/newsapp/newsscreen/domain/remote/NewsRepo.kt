package eg.gov.iti.jets.newsapp.newsscreen.domain.remote

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel

interface NewsRepo {

    suspend fun getNews (country : String = "US") : NewsModel
}