package work.prgrm.todolist.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import work.prgrm.todolist.entity.TodolistWithCategoriesEntity

@Dao
interface TodolistWithCategoriesDao {
    @Transaction
    @Query("select * from todo_table")
    suspend fun selectAll():MutableList<TodolistWithCategoriesEntity>
}