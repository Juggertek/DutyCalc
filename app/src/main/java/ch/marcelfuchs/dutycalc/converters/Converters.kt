package ch.marcelfuchs.dutycalc.converters

import androidx.room.TypeConverter
import ch.marcelfuchs.dutycalc.model.DutyDay
import com.google.gson.Gson
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

    @TypeConverter
    fun dutyDayToString(dutyDay: DutyDay): String = Gson().toJson(dutyDay)

    @TypeConverter
    fun stringToDutyDay(string: String): DutyDay = Gson().fromJson(string, DutyDay::class.java)
}