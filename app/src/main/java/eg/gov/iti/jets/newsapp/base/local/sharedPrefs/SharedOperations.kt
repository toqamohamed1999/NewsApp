package eg.gov.iti.jets.newsapp.base.local.sharedPrefs

import android.content.Context
import android.content.SharedPreferences

class SharedOperations private constructor() {

    companion object {
        private var instance :SharedOperations? = null
        private lateinit var  context: Context

        private lateinit var sharedPrefs: SharedPreferences
        fun initSharedPrefs(context: Context) {
            this.context = context
            sharedPrefs =
                Companion.context.applicationContext.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)
        }

        fun getInstance(): SharedPreferences{
            return  context.getSharedPreferences("CurrentUser",Context.MODE_PRIVATE)
        }



        fun deleteCurrentUser(){
           val temp =  sharedPrefs.edit()
            temp.clear()
            temp.apply()
        }
        fun setCurrentUserData(userName: String, password: String, email: String) {
            val tempPrefs = sharedPrefs.edit()

            tempPrefs.putString("userName", userName)
            tempPrefs.putString("password", password)
            tempPrefs.putString("email", email)
            tempPrefs.apply()
        }

        fun getCurrentUserData(): Triple<String?, String?, String?> {
            return Triple(
                sharedPrefs.getString("userName", ""),
                sharedPrefs.getString("password", ""),
                sharedPrefs.getString("email", "")
            )
        }
    }
}