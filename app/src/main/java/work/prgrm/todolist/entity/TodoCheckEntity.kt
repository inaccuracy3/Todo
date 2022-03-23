package work.prgrm.todolist.entity

import androidx.room.*


@Entity(tableName = "todo_check_table")
data class TodoCheckEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id:Int,
)
