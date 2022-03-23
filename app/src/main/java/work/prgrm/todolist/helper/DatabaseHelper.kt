package work.prgrm.todolist.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context,DBname:String,DBVersion:Int) :SQLiteOpenHelper(context,DBname,null,DBVersion){
    override fun onCreate(DB:SQLiteDatabase){
        val sql = "CREATE TABLE IF NOT EXISTS test_table(id INTERGER PRIMARY KEY,todo TEXT)"
        DB.execSQL(sql)
    }
    override fun onUpgrade(DB:SQLiteDatabase?,OldVersion:Int,NewVersion:Int){}

    fun fileDelete(DBName:String){
        context.deleteDatabase(DBName)
    }
}