package com.alexpletnyov.crypto_rate.utility

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimestampToTime(timestamp: Long?, pattern: String): String {
	if (timestamp == null) return ""
	val stamp = Timestamp(timestamp * 1000)
	val date = Date(stamp.time)
	val sdf = SimpleDateFormat(pattern, Locale.getDefault())
	sdf.timeZone = TimeZone.getDefault()
	return sdf.format(date)
}

object TimePatterns {
	const val TIME = "HH:mm:ss"
	const val DATA_AND_TIME = "yy-MM-dd HH:mm:ss"
}