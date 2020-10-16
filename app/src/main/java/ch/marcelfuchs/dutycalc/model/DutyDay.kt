package ch.marcelfuchs.dutycalc.model

import java.sql.Date


data class DutyDay(
    val date: Date,
    val hasStby:Boolean,
    val standByStart: String?=null,
    val standByEnd: String?=null,
    val showTime: String,
    val closingTime: String,
    val dutyTime: Float
)