package ch.marcelfuchs.dutycalc.repository

import androidx.lifecycle.LiveData
import ch.marcelfuchs.dutycalc.data.TourDao
import ch.marcelfuchs.dutycalc.model.Tour

class TourRepository(private val tourDao: TourDao) {

    val readAllData: LiveData<List<Tour>> = tourDao.readAllData()

    suspend fun addTour(tour: Tour) {
        tourDao.addTour(tour)
    }

    suspend fun updateTour(tour: Tour) {
        tourDao.updateTour(tour)
    }

    suspend fun deleteTour(tour: Tour) {
        tourDao.deleteTour(tour)
    }

    suspend fun deleteAllTours() {
        tourDao.deleteAllTours()
    }

}