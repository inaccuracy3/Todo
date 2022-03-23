package work.prgrm.todolist.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.prgrm.todolist.application.App
import work.prgrm.todolist.entity.TodoEntity

class TodoRepository {
    private val dao = App.database.TodoDao()

    suspend fun insert(todo:TodoEntity){
        withContext(Dispatchers.IO){
            dao.insert(todo)
        }
    }

    suspend fun selectLast():TodoEntity{
        return withContext(Dispatchers.IO){
            dao.selectLast()
        }
    }

    suspend fun select(id:Int):TodoEntity{
        return withContext(Dispatchers.IO){
            dao.select(id)
        }
    }

    suspend fun selectAll():MutableList<TodoEntity>{
        return withContext(Dispatchers.IO){
            dao.selectAll()
        }
    }
}