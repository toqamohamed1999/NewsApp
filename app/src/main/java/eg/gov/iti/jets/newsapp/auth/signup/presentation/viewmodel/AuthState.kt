package eg.gov.iti.jets.newsapp.auth.signup.presentation.viewmodel

sealed class AuthState {
    class onSuccess(val message: Int) : AuthState()
    class onError(val message: Int) : AuthState()

    object BeforeValidation : AuthState()

}
