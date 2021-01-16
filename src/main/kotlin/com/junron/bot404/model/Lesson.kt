package com.junron.bot404.model

import kotlinx.serialization.Serializable
import java.util.*
import kotlin.math.floor

@Serializable
data class Lesson(
    val subjects: List<String>,
    val day: String,
    val timeStart: Int,
    val timeEnd: Int
) {
    private val endHour = floor(timeEnd / 100.0).toInt()
    private val endMinute = timeEnd % 100
    private val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    fun getNextLesson(from: Date = Date()): Date {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
            .apply {
                set(Calendar.DAY_OF_WEEK, days.indexOf(day) + 2)
                set(Calendar.HOUR_OF_DAY, endHour)
                set(Calendar.MINUTE, endMinute)
                set(Calendar.SECOND, 0)
            }
        if (calendar.time.time < from.time) {
            calendar.add(Calendar.DAY_OF_YEAR, 7)
        }
        return calendar.time
    }

}

