package ru.pakad.pakadcripto.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

// Преобразование секунд во время

fun convertTimestampToTime(timestamp: String): String {
    //if (timestamp == null) return ""
   // val timestampLong = timestamp.toLong()
    val stamp = Timestamp(timestamp.toLong() * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)

}