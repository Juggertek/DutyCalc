package ch.marcelfuchs.dutycalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "tour_table")
data class Tour(
    @PrimaryKey
    val day1: DutyDay,
    val day2: DutyDay?=null,
    val day3: DutyDay?=null,
    val day4: DutyDay?=null,
    val day5: DutyDay?=null,
    val day6: DutyDay?=null,
    val day7: DutyDay?=null,
    val totalHours: Float
)