package work.prgrm.todolist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import work.prgrm.todolist.entity.TodoCheckEntity

@Dao
interface TodoCheckDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoCheckEntity: TodoCheckEntity)

    @Query("select * from todo_check_table")
    suspend fun  selectAll():MutableList<TodoCheckEntity>
}