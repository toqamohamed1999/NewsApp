package eg.gov.iti.jets.newsapp.auth.login.data.model

data class LoginResponse (val kind : String , val localId : String , val email : String , val displayName : String , val idToken :
String , val registered : Boolean , val refreshToken : String , val expiresIn : String)