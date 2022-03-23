package work.prgrm.todolist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "categoryId")val id:Long,
    @ColumnInfo(name="name")val name:String,
    @ColumnInfo(name="color")val color:Int,
):Serializable
