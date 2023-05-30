package eg.gov.iti.jets.newsapp.base.local.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import eg.gov.iti.jets.newsapp.util.Constants

class SharedOperations private constructor() {

    companion object {
        private var instance: SharedOperations? = null

        private lateinit var sharedPrefs: SharedPreferences
        fun initSharedPrefs(context: Context) {

            sharedPrefs =
                context.applicationContext.getSharedPreferences(
                    "CurrentUser",
                    Context.MODE_PRIVATE
                )
        }

        fun deleteCurrentUser(): Boolean {
            val temp = sharedPrefs.edit()
            temp.clear()
            return temp.commit()
        }

        fun setCurrentUserData(userName: String, token: String, email: String) {
            val tempPrefs = sharedPrefs.edit()
            tempPrefs.putString(Constants.USER_NAME, userName)
            tempPrefs.putString(Constants.TOKEN, token)
            tempPrefs.putString(Constants.EMAIL, email)
            tempPrefs.apply()
        }

        fun getCurrentUserData(): Triple<String?, String?, String?> {
            return Triple(
                sharedPrefs.getString(Constants.USER_NAME, ""),
                sharedPrefs.getString(Constants.TOKEN, ""),
                sharedPrefs.getString(Constants.EMAIL, "")
            )
        }
    }
}