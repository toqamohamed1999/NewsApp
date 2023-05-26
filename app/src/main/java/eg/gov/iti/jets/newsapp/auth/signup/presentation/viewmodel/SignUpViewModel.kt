package eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.BuildConfig
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.domain.repo.AuthRepo
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(private val repo: AuthRepo) : ViewModel() {

    private val validationMutableStateFlow =
        MutableStateFlow<AuthState>(AuthState.BeforeValidation)
    val validationStateFlow: StateFlow<AuthState> = validationMutableStateFlow

    fun validateInputs(signUpModel: SignUpModel) {
        if (signUpModel.displayName.isEmpty()) {
            validationMutableStateFlow.value = AuthState.onError(R.string.userNameEmpty)
        } else if (signUpModel.email.isEmpty()) {
            validationMutableStateFlow.value = AuthState.onError(R.string.emailNameEmpty)
        } else if (signUpModel.password.isEmpty()) {
            validationMutableStateFlow.value = AuthState.onError(R.string.passwordEmpty)
        } else if (validateEmail(signUpModel)) {
            validationMutableStateFlow.value = AuthState.onError(R.string.validateEmail)
        } else if (!isValidPassword(signUpModel.password)) {
            validationMutableStateFlow.value = AuthState.onError(R.string.validatePassword)
        } else {
            signUpUser(signUpModel)
        }
    }

    private fun validateEmail(signUpModel: SignUpModel) =
        !Patterns.EMAIL_ADDRESS.matcher(signUpModel.email).matches()

    private fun signUpUser(signUpModel: SignUpModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                repo.signUpSignUpModel(key = BuildConfig.API_KEY, userModel = signUpModel)
            saveUserData(
                userName = response.displayName,
                email = response.email,
                token = response.refreshToken
            )
            validationMutableStateFlow.value = AuthState.onSuccess(R.string.success)
        }
    }


    private fun saveUserData(userName: String, email: String, token: String) {
        SharedOperations.setCurrentUserData(userName = userName, email = email, token = token)
    }

    private fun isValidPassword(password: String?): Boolean {
        val regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

}