package eg.gov.iti.jets.newsapp.base.local.sharedPrefs

import android.app.Application

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedOperations.initSharedPrefs(this.applicationContext)
    }
}