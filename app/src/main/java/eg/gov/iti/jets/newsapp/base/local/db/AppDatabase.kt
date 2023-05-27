package eg.gov.iti.jets.newsapp.base.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleDao
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article


@Database(entities = [Article::class], version = 6)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getArticleDao() :ArticleDao

    companion object{
        @Volatile
        private var instance : AppDataBase? = null

        fun getInstance(context : Context):AppDataBase{
            return instance ?: synchronized(this){

                instance = Room.databaseBuilder(context,AppDataBase::class.java,"appdatabase")
                    .fallbackToDestructiveMigration().build()

                instance!!
            }
        }

    }
}