package eg.gov.iti.jets.newsapp.auth.signup.data.model

data class SignUpResponse(val kind : String, val idToken : String,
                          val displayName : String, val email : String, val refreshToken : String,
                          val expiresIn : String, val localId : String)
