package work.prgrm.todolist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import work.prgrm.todolist.entity.TodoWithCategoryEntity

@Dao
interface TodoWithCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TodoWithCategoryEntity)

    @Query("select * from todo_with_category_table")
    suspend fun selectAll():List<TodoWithCategoryEntity>
}