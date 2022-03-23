package work.prgrm.todolist.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.prgrm.todolist.application.App
import work.prgrm.todolist.entity.CategoryEntity
import work.prgrm.todolist.entity.TodoEntity

class CategoryRepository {
    private val dao = App.database.CategoryDao()
    suspend fun insert(category: CategoryEntity){
        withContext(Dispatchers.IO){
            dao.insert(category)
        }
    }

    suspend fun selectAll():MutableList<CategoryEntity>{
        return withContext(Dispatchers.IO){
            dao.selectAll()
        }
    }

    suspend fun update(category:CategoryEntity){
        withContext(Dispatchers.IO){
            dao.update(category)
        }
    }

    suspend fun delete(category:CategoryEntity){
        withContext(Dispatchers.IO){
            dao.delete(category)
        }
    }
}