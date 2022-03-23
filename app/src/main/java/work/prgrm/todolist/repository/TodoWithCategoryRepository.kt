package work.prgrm.todolist.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.prgrm.todolist.application.App
import work.prgrm.todolist.entity.TodoWithCategoryEntity

class TodoWithCategoryRepository {
    private val dao = App.database.TodoWithCategoryDao()

    suspend fun insert(todoWithCategory:TodoWithCategoryEntity){
        withContext(Dispatchers.IO){
            dao.insert(todoWithCategory)
        }
    }

    suspend fun selectAll(){
        return withContext(Dispatchers.IO){
            dao.selectAll()
        }
    }
}