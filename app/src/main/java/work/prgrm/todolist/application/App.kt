package work.prgrm.todolist.application

import android.app.Application
import android.content.res.Resources
import android.util.Log
import work.prgrm.todolist.database.AppDatabase
import work.prgrm.todolist.entity.CategoryEntity
import java.util.*

class App:Application() {
    companion object {
        lateinit var database: AppDatabase
        lateinit var res:Resources
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getInstance(applicationContext)
        res = resources
        Locale.setDefault(Locale.JAPAN)
        Log.i("onCreateApp","start")
    }
}