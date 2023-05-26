package eg.gov.iti.jets.newsapp.auth.domain.repo

import eg.gov.iti.jets.newsapp.auth.domain.model.User

interface AuthRepo {

  suspend  fun sigUp(email : String, password : String): User
}