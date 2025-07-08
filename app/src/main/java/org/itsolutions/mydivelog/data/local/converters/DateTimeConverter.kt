package org.itsolutions.mydivelog.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDate

@ProvidedTypeConverter
class DateTimeConverter {
    @TypeConverter
    fun fromString(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        return date?.toString()
    }
}