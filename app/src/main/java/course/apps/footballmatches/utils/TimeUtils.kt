package course.apps.footballmatches.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimestampToDate(timestamp: Long?) : String
{
    if (timestamp == null) return ""
    val stamp = Timestamp(timestamp*1000)
    val date = Date(stamp.time)
    val pattern = "E, d MMM yy, H:mm"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}