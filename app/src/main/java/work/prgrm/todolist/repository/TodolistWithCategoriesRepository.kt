package work.prgrm.todolist.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.prgrm.todolist.application.App
import work.prgrm.todolist.dao.TodolistWithCategoriesDao
import work.prgrm.todolist.entity.TodolistWithCategoriesEntity

class TodolistWithCategoriesRepository {
    val dao = App.database.TodolistWithCategoriesDao()

    suspend fun selectAll():MutableList<TodolistWithCategoriesEntity>{
        return withContext(Dispatchers.IO){
            dao.selectAll()
        }
    }

}