package eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eg.gov.iti.jets.newsapp.auth.domain.repo.AuthRepo

class SignUpViewModelProvider(private val repoInterface : AuthRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            SignUpViewModel(repo = repoInterface) as T
        } else {
            throw IllegalArgumentException("Sign Up ViewModel is not existed")
        }
    }

}