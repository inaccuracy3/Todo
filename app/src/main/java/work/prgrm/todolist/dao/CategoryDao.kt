package work.prgrm.todolist.dao

import androidx.room.*
import work.prgrm.todolist.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category:CategoryEntity)

    @Query("select * from category_table")
    suspend fun selectAll():MutableList<CategoryEntity>

    @Update
    suspend fun update(category:CategoryEntity)

    @Delete
    suspend fun delete(category: CategoryEntity)
}