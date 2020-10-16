package ch.marcelfuchs.dutycalc.util

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter
@BindingAdapter("app:hideIfUnchecked")
fun hideIfUnchecked(view: View, state: Boolean) {
    view.visibility = if (state) View.VISIBLE else View.GONE
}