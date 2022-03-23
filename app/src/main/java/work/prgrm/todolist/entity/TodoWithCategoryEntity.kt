package work.prgrm.todolist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "todo_with_category_table", primaryKeys = ["todoId","categoryId"])
data class TodoWithCategoryEntity(
@ColumnInfo(name = "todoId")val todoId:Long,
@ColumnInfo(name="categoryId", index = true)val categoryId:Long
)
