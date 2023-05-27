package eg.gov.iti.jets.newsapp.newsdetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel.LoginViewModel
import eg.gov.iti.jets.newsapp.newsdetails.domain.repo.NewsDetailsRepo

class NewsDetailsViewModelFactory(val repo:NewsDetailsRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)){
            NewsDetailsViewModel(repo) as T
        }else{
            throw IllegalArgumentException()
        }
    }
}