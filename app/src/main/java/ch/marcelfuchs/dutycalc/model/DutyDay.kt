package ch.marcelfuchs.dutycalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "dutyDay_table")
data class DutyDay(
    @PrimaryKey(autoGenerate = true)
    val dutyDayId:Long,
    val date: Date,
    val hasStby:Boolean,
    val standByStart: String?=null,
    val standByEnd: String?=null,
    val showTime: String,
    val closingTime: String,
    val dutyTime: Float
)