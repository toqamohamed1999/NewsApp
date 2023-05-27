package eg.gov.iti.jets.newsapp.profile.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel
import eg.gov.iti.jets.newsapp.profile.domain.repo.ProfileRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repo: ProfileRepoInterface) : ViewModel() {

    private val userMSharedFlow = MutableStateFlow<UserModel?>(null)
    val userSharedFlow: Flow<UserModel?> = userMSharedFlow

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = repo.getUser()
            userMSharedFlow.emit(user)
            Log.e("TAG1", user.userName)
            Log.e("TAG1", user.email)
        }
    }

    fun deleteUser(): Boolean {
        var isLogout = false
        viewModelScope.launch {
            isLogout = repo.deleteUser()
        }
        return isLogout
    }
}