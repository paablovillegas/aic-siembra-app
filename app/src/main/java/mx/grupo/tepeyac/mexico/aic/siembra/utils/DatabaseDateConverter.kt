package mx.grupo.tepeyac.mexico.aic.siembra.utils

import androidx.room.TypeConverter
import java.util.*

class DatabaseDateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}