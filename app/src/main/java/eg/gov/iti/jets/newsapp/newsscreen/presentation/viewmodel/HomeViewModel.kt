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

class HomeViewModel(private val newsRepo: NewsRepo) : ViewModel() {

    private var _newsState: MutableStateFlow<NewsResultState> = MutableStateFlow(
        NewsResultState.Loading()
    )
    private val _stateAutoComplete:MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val stateAutoComplete:StateFlow<List<String>> = _stateAutoComplete
    private val  _articleSearchState:MutableStateFlow<List<Article>> = MutableStateFlow(listOf())
    val  articleSearchState:StateFlow<List<Article>> =  _articleSearchState

    private var articles = listOf<Article>()
    var newsState: StateFlow<NewsResultState> = _newsState

     fun getNews() {
        viewModelScope.launch {
            try {
                newsRepo.getNews().collect {
                    _newsState.value = NewsResultState.Success(it)
                    articles = it
                }
            } catch (e: java.lang.Exception) {
                _newsState.value = NewsResultState.Error()
            }
        }
    }

    fun searchArticles(query: String?) {
        if(query.isNullOrBlank()){
            _articleSearchState.value = articles
        }
        val filteredArticles = mutableListOf<Article>()
        for (article in articles) {
            if (article.title.contains(query!!, true)) {
                filteredArticles.add(article)
            }
        }
        _articleSearchState.value = filteredArticles
    }
    fun search(query: String?) {
        if(query.isNullOrBlank()){
            return
        }
        val filteredTitleArticles = mutableListOf<String>()
        for (element in articles) {
            val stringTitle = element.title
            filteredTitleArticles.add(stringTitle)
        }
        _stateAutoComplete.value =  filteredTitleArticles
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