package eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.newsscreen.data.model.NewsResultState
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
                newsRepo.getNews().collect {
                    _newsState.value = NewsResultState.Success(it)
                    newsRepo.deleteAllArticles()
                    newsRepo.insertArticles(it)
                }
            } catch (e: java.lang.Exception) {
                _newsState.value = NewsResultState.Error()
            }
        }
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