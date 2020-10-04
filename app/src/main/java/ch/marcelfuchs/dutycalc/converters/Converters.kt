package ch.marcelfuchs.dutycalc.converters

import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Time

class Converters {
    @TypeConverter
    fun longToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun longToTime(value: Long?): Time? {
        return value?.let { Time(it) }
    }

    @TypeConverter
    fun timeToLong(date: Time?): Long? {
        return date?.time
    }
}