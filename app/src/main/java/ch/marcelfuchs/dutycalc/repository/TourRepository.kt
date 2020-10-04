package ch.marcelfuchs.dutycalc.repository

import androidx.lifecycle.LiveData
import ch.marcelfuchs.dutycalc.data.TourDao
import ch.marcelfuchs.dutycalc.model.Tour

class TourRepository(private val userDao: TourDao) {

    val readAllData: LiveData<List<Tour>> = userDao.readAllData()

    suspend fun addUser(tour: Tour) {
        userDao.addTour(tour)
    }

    suspend fun updateUser(tour: Tour) {
        userDao.updateTour(tour)
    }

    suspend fun deleteUser(tour: Tour) {
        userDao.deleteTour(tour)
    }

    suspend fun deleteAllTours() {
        userDao.deleteAllTours()
    }

}