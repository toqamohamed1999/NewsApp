package eg.gov.iti.jets.newsapp.profile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eg.gov.iti.jets.newsapp.profile.data.repo.ProfileRepo
import eg.gov.iti.jets.newsapp.profile.domain.repo.ProfileRepoInterface

class ProfileViewModelProvider(private val repoInterface: ProfileRepoInterface) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            ProfileViewModel(repoInterface) as T
        } else {
            throw IllegalArgumentException("View model class cannot be found")
        }
    }
}