package ch.marcelfuchs.dutycalc.util

import android.view.View
import androidx.databinding.BindingAdapter
import java.sql.Time

@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number: Int) {
    view.visibility = if (number == 0) View.VISIBLE else View.INVISIBLE
}