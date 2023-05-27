package eg.gov.iti.jets.newsapp.newsdetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import eg.gov.iti.jets.newsapp.newsdetails.domain.repo.NewsDetailsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsDetailsViewModel (private  val newsDetailsNewsRepo: NewsDetailsRepo) : ViewModel() {

    private var _isFavorite : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite : StateFlow<Boolean> = _isFavorite


    fun addNewsToFavorite(articleModel: FavouriteArticleModel){
        viewModelScope.launch(Dispatchers.IO) {
            newsDetailsNewsRepo.addToFavoriteNews(articleModel)
        }
    }
    fun isFavorite(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
         _isFavorite.value =  newsDetailsNewsRepo.isFavorite(id)
        }
    }
    fun deleteFromFavorite(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            launch {
                newsDetailsNewsRepo.deleteFromFavorite(id)
            }.join()
            launch {
                _isFavorite.value = false
            }
        }

    }

}