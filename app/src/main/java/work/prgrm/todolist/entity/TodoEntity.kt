package work.prgrm.todolist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo_table")
data class TodoEntity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="todoId")var id:Long,
    @ColumnInfo(name="todoText")var todoText:String,
    @ColumnInfo(name="startDateText")var startDateText:String?,
    @ColumnInfo(name="goalDateText")var goalDateText:String?,
    @ColumnInfo(name="deadlineDateText")var deadlineDateText:String,
    @ColumnInfo(name = "isDone")var isDone: IsDone,
    @ColumnInfo(name = "importanceType")var importanceType: ImportanceType,
    @ColumnInfo(name = "termType")var termType: TermType
):Serializable

