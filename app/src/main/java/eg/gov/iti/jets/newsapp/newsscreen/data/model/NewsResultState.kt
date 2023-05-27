package eg.gov.iti.jets.newsapp.newsscreen.data.model

import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article


sealed class NewsResultState{
    class Success(val articleList: List<Article>): NewsResultState()
    class Loading(val message:String = "loading"):NewsResultState()
    class Error(val error:String="Error"):NewsResultState()
}