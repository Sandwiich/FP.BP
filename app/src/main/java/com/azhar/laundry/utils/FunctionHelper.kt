package com.azhar.laundry.utils

import android.text.format.DateFormat
import java.text.DecimalFormat
import java.util.Calendar

object FunctionHelper {
    @JvmStatic
    fun rupiahFormat(price: Int): String {
        val formatter = DecimalFormat("#,###")
        return "Rp " + formatter.format(price.toLong()).replace(",".toRegex(), ".")
    }

    @JvmStatic
    val today: String
        get() {
            val date = Calendar.getInstance().time
            return DateFormat.format("d MMMM yyyy", date) as String
        }
}