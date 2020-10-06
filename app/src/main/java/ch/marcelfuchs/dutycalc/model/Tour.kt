package ch.marcelfuchs.dutycalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "tour_table")
data class Tour(
    @PrimaryKey
    val day1: DutyDay,
    val day2: DutyDay,
    val day3: DutyDay,
    val day4: DutyDay,
    val day5: DutyDay,
    val day6: DutyDay,
    val day7: DutyDay,
    val totalHours: Time
)