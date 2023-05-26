package eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel

sealed class APIState{
    object OnSuccess :APIState()
    class OnFail(val errorMessage: Throwable ):APIState()
    object Loading : APIState()
}