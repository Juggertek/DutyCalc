package ch.marcelfuchs.dutycalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tour_table")
data class Tour(
    @PrimaryKey
    val dutyDayList: List<DutyDay>,
    val totalHours: Float
)