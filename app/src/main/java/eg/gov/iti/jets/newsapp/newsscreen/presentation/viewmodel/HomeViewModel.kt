package eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.newsscreen.data.model.NewsResultState
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.repo.NewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val newsRepo: NewsRepo): ViewModel() {

    private var _newsState: MutableStateFlow<NewsResultState> = MutableStateFlow(
        NewsResultState.Loading()
    )
    var newsState: StateFlow<NewsResultState> = _newsState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            try {
                 newsRepo.getNews().collect{
                    _newsState.value = NewsResultState.Success(it.articles)
                }
            } catch (e: java.lang.Exception) {
                _newsState.value = NewsResultState.Error()
            }
        }
    }

    private fun getStoredNews() {
        viewModelScope.launch {
            try {
                newsRepo.getStoredNews().collect{
                    _newsState.value = NewsResultState.Success(it)
                }
            } catch (e: java.lang.Exception) {
                _newsState.value = NewsResultState.Error()
            }
        }
    }

    fun searchArticles(articles: List<Article>, query: String): List<Article> {
        val filteredArticles = mutableListOf<Article>()
        for (article in articles) {
            if (article.title.contains(query, true)) {
                filteredArticles.add(article)
            }
        }
        if(filteredArticles.isEmpty()){
            println("NOTFounddd!!!!!!!!!!")
        }else
        println("filteredArticles[0]............ ${filteredArticles[0]}")
        return filteredArticles

    }

    fun getTitleArtical(articles: List<Article>): List<String> {
        var filteredTitleArticles = mutableListOf<String>()
        for (i in 0..articles.size-1) {
            var stringTitle = articles[i].title
            filteredTitleArticles.add(stringTitle)
        }
        return filteredTitleArticles
    }


}

class HomeViewModelFactory(private val repo: NewsRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(repo) as T
        } else {
            throw java.lang.IllegalArgumentException("HomeViewModel class not found")
        }
    }
}