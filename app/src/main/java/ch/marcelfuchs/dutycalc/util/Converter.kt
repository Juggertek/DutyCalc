package ch.marcelfuchs.dutycalc.util

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

object Converter {
    @JvmStatic
    fun dateToString(value: Time?): String? {
        return value?.toString()?.substring(0, 5)
    }

    @JvmStatic
    fun dateFormat(date: Date): String {
        val dateFormat = SimpleDateFormat("dd. MMM y", Locale.getDefault())
        return dateFormat.format(date)
    }

    @JvmStatic
    fun floatToString(float: Float): String {
        val hours = float.toInt()
        val minutes = ((float - hours) * 60).toInt()
        return hours.toString() + ":" + minutes.toString()
    }
}