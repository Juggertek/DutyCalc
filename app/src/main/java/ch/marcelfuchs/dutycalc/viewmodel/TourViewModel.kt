package ch.marcelfuchs.dutycalc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ch.marcelfuchs.dutycalc.data.TourDatabase
import ch.marcelfuchs.dutycalc.model.Tour
import ch.marcelfuchs.dutycalc.repository.TourRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TourViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Tour>>
    private val repository: TourRepository

    init {
        val tourDao = TourDatabase.getDatabase(
            application
        ).tourDao()
        repository = TourRepository(tourDao)
        readAllData = repository.readAllData
    }

    fun addTour(tour: Tour){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTour(tour)
        }
    }

    fun updateTour(tour: Tour){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTour(tour)
        }
    }

    fun deleteTour(tour: Tour){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTour(tour)
        }
    }

    fun deleteAllTours(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTours()
        }
    }
}