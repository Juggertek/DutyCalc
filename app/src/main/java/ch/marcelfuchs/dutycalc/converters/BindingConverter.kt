package ch.marcelfuchs.dutycalc.converters

import android.widget.EditText
import androidx.databinding.InverseMethod
import java.sql.Time

object BindingConverter {
    @InverseMethod("stringToTime")
    @JvmStatic
    fun timeToString(view: EditText, oldValue: Time, value: Time): String {
        return value.toString().dropLast(3)
    }

    @JvmStatic
    fun stringToTime(view: EditText, oldValue: String, value: String): Time {
        return Time.valueOf(value)
    }
}