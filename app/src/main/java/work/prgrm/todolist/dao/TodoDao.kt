package work.prgrm.todolist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import work.prgrm.todolist.entity.TodoEntity

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo:TodoEntity)

    @Query("select * from todo_table where todoId=:id")
    suspend fun select(id:Int):TodoEntity

    @Query("select * from todo_table")
    suspend fun selectAll():MutableList<TodoEntity>

    @Query("select * from todo_table order by todoId desc limit 1")
    suspend fun selectLast():TodoEntity
}