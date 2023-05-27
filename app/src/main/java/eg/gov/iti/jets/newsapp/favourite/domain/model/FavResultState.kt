package eg.gov.iti.jets.newsapp.favourite.domain.model

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article

sealed class FavResultState {

    class Success(val articleList: List<FavouriteArticleModel>): FavResultState()
    class Loading(val message:String = "loading"): FavResultState()
    class Error(val error:String="Error"): FavResultState()
}