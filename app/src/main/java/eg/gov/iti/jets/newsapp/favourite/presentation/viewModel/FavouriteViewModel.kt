package eg.gov.iti.jets.newsapp.favourite.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavResultState
import eg.gov.iti.jets.newsapp.favourite.domain.repo.FavRepoInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repo: FavRepoInterface): ViewModel() {

    private var _favState: MutableStateFlow<FavResultState> = MutableStateFlow(
        FavResultState.Loading()
    )
    var favState: StateFlow<FavResultState> = _favState

    init {
        getStoredFav()
    }

    private fun getStoredFav() {
        viewModelScope.launch {
            try {
                repo.getAllFavourite().collect {
                    _favState.value = FavResultState.Success(it)
                }
            } catch (e: java.lang.Exception) {
                _favState.value = FavResultState.Error()
            }
        }
    }

}

class FavViewModelFactory(private val repo: FavRepoInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            FavouriteViewModel(repo) as T
        } else {
            throw java.lang.IllegalArgumentException("FavViewModel class not found")
        }
    }
}