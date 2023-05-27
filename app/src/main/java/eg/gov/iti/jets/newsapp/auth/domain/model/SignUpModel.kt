package eg.gov.iti.jets.newsapp.auth.domain.model

data class SignUpModel(val email : String, val password : String,
                       val displayName : String , val returnSecureToken : Boolean = true)
