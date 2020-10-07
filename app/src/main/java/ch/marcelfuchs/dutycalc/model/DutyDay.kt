package ch.marcelfuchs.dutycalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity(tableName = "dutyday_table")
data class DutyDay(
    @PrimaryKey
    val date: Date,
    val standByStart: Time?,
    val standByEnd: Time?,
    val showTime: Time,
    val closingTime: Time
)