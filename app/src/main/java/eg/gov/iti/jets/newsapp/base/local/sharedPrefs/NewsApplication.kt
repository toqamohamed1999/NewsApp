package eg.gov.iti.jets.newsapp.base.local.sharedPrefs

import android.app.Application
import eg.gov.iti.jets.newsapp.base.local.db.AppDataBase
import eg.gov.iti.jets.newsapp.util.NetworkConnectivityObserver

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedOperations.initSharedPrefs(this.applicationContext)
        AppDataBase.intiRoom(applicationContext)
        NetworkConnectivityObserver.initNetworkConnectivity(applicationContext)

    }
}