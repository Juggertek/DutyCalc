package ch.marcelfuchs.dutycalc.repository

import androidx.lifecycle.LiveData
import ch.marcelfuchs.dutycalc.data.DutyDayDao
import ch.marcelfuchs.dutycalc.model.DutyDay

class DutyDayRepository(private val dutyDayDao: DutyDayDao) {

    val tourList: LiveData<List<DutyDay>> = dutyDayDao.readAllData()

    suspend fun addTour(dutyDay: DutyDay) {
        dutyDayDao.addDutyDay(dutyDay)
    }

    suspend fun updateTour(dutyDay: DutyDay) {
        dutyDayDao.updateDutyDay(dutyDay)
    }

    suspend fun deleteTour(dutyDay: DutyDay) {
        dutyDayDao.deleteDutyDay(dutyDay)
    }

    suspend fun deleteAllTours() {
        dutyDayDao.deleteAllDutyDays()
    }

}