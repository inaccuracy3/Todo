package work.prgrm.todolist.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import work.prgrm.todolist.entity.IsDone

@ProvidedTypeConverter
internal class IsDoneConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toIsDone(value: String): IsDone {
            return IsDone.valueOf(value)
        }

        @TypeConverter
        @JvmStatic
        fun fromIsDone(value: IsDone): String = value.name
    }
}