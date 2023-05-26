package eg.gov.iti.jets.newsapp.base.local.sharedPrefs

import android.content.Context
import android.content.SharedPreferences

class SharedOperations private constructor(context:Context) {
    private val sharedPrefs:SharedPreferences
    init {
        sharedPrefs = context.applicationContext.getSharedPreferences("CurrentUser",Context.MODE_PRIVATE)
    }
    fun setCurrentUserData(userName:String,password:String,email:String){
        val tempPrefs = sharedPrefs.edit()

        tempPrefs.putString("userName",userName)
        tempPrefs.putString("password",password)
        tempPrefs.putString("email",email)
        tempPrefs.apply()
    }
    fun getCurrentUserData():Triple<String?,String?,String?>{
      return Triple(sharedPrefs.getString("userName",""),sharedPrefs.getString("password",""),sharedPrefs.getString("email",""))
    }
    companion object {
       private var instance :SharedOperations? = null
       fun getInstance(context:Context):SharedOperations?{
           if(instance == null)
               instance = SharedOperations(context)
           return  instance
       }
    }
}