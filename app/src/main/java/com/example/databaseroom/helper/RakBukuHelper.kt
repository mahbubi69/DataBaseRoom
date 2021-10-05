package com.example.databaseroom.helper

import java.text.SimpleDateFormat
import java.util.*

//pembantu
class RakBukuHelper {

    companion object {
        fun getTodayDate(): String {
            val tanggal = Calendar.getInstance().time
            val dataFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
            return dataFormat.format(tanggal)
        }
    }
}