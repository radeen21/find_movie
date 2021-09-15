package com.example.base.util

import java.text.NumberFormat
import java.util.*

open class NumberFormater {
    companion object {
        fun usFormat(value: Int?): String {
            try {
                value?.let {
                    return NumberFormat.getIntegerInstance(Locale.US).format(it)
                }
            } catch (exception: NumberFormatException) {
                return ""
            }
            return ""
        }
    }
}