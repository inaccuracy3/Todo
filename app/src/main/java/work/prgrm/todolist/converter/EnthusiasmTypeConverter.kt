package work.prgrm.todolist.converter

import androidx.room.TypeConverter
import work.prgrm.todolist.entity.ImportanceType

class EnthusiasmTypeConverter {
                @TypeConverter
                fun toEnthusiasmType(value: String): ImportanceType {
                        return ImportanceType.valueOf(value)
                }
                @TypeConverter
                fun fromEnthusiasmType(value: ImportanceType): String? {
                        return value.name
                }

}