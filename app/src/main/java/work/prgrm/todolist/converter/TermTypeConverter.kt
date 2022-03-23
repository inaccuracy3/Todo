package work.prgrm.todolist.converter

import androidx.room.TypeConverter
import work.prgrm.todolist.entity.TermType


internal class TermTypeConverter {
        companion object {
                @TypeConverter
                fun toTermType(value: String): TermType {
                        return TermType.valueOf(value)
                }

                @TypeConverter
                fun fromTermType(value: TermType): String = value.name
        }
}