package ch.marcelfuchs.dutycalc.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object Converter {
//    @InverseMethod("stringToDate")
//    @JvmStatic
//    fun dateToString(value: Time?): String? {
//        return value?.toString()?.substring(0, 5)
//    }
//
//    @JvmStatic
//    fun stringToDate(value: String?): Time? {
//        return Time.valueOf(value)
//    }

    @JvmStatic
    fun dateFormat(date: Date): String {
        val dateFormat = SimpleDateFormat("dd. MMM y", Locale.getDefault())
        return dateFormat.format(date)
    }

    @JvmStatic
    fun floatToString(float: Float): String {
        val hours = float.toInt()
        val hoursString = hours.toString().padStart(2, '0')
        val minutes = ((float - hours) * 60).roundToInt()
        val minutesString = minutes.toString().padStart(2, '0')
        return hoursString + ":" + minutesString
    }
}