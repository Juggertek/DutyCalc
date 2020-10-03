package ch.marcelfuchs.dutycalc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "tour_table")
data class Tour(
    @PrimaryKey
    val firstDayofTour: Date,
    val lastDayOfTour: Date,
    val numberOfDaysInTour: Byte
)