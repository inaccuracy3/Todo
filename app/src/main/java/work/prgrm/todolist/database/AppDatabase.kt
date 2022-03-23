package work.prgrm.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import work.prgrm.todolist.R
import work.prgrm.todolist.application.App
import work.prgrm.todolist.dao.CategoryDao
import work.prgrm.todolist.entity.TodoEntity
import work.prgrm.todolist.dao.TodoDao
import work.prgrm.todolist.dao.TodoWithCategoryDao
import work.prgrm.todolist.dao.TodolistWithCategoriesDao
import work.prgrm.todolist.entity.CategoryEntity
import work.prgrm.todolist.entity.TodoWithCategoryEntity


//TypeConverter作ったら入れる
//@TypeConverters()
@Database(entities = [TodoEntity::class,TodoWithCategoryEntity::class,CategoryEntity::class],version = 1,exportSchema = false)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun TodoDao():TodoDao
    abstract fun CategoryDao():CategoryDao
    abstract fun TodoWithCategoryDao():TodoWithCategoryDao
    abstract fun TodolistWithCategoriesDao():TodolistWithCategoriesDao
    companion object {
        @Volatile
        private var database: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (database == null) {
                synchronized(this) {
                    database = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            @Suppress("DEPRECATION") val initialColor = App.res.getColor(R.color.purple_700)
                            val sql = "INSERT INTO 'category_table' VALUES (0, '一般', ${initialColor})"
                            db.execSQL(sql)
                        }
                    }).build()
                }
            }
            return requireNotNull(database)
        }
    }
}