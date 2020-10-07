package ch.marcelfuchs.dutycalc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ch.marcelfuchs.dutycalc.data.TourDatabase
import ch.marcelfuchs.dutycalc.model.Tour
import ch.marcelfuchs.dutycalc.repository.TourRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

// Konstante zur Addition/Subtraktion eines Tages.
private const val DAY_IN_MILLISECONDS: Long = 1000 * 60 * 60 * 24

class ViewModel(application: Application) : AndroidViewModel(application) {

    val _newDutyDayDate: MutableLiveData<Date> = MutableLiveData(Date(System.currentTimeMillis()))
    var newDutyDayDate: LiveData<Date> = _newDutyDayDate

    // Variable mit aktuellem Tag erstellen.
//    var newDutyDayDate = Date(System.currentTimeMillis())

    val readAllData: LiveData<List<Tour>>
    private val repository: TourRepository

    init {
        val tourDao = TourDatabase.getDatabase(
            application
        ).tourDao()
        repository = TourRepository(tourDao)
        readAllData = repository.readAllData
    }

    fun addTour(tour: Tour) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTour(tour)
        }
    }

    fun updateTour(tour: Tour) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTour(tour)
        }
    }

    fun deleteTour(tour: Tour) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTour(tour)
        }
    }

    fun deleteAllTours() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTours()
        }
    }

    fun decreaseDay() {
        val date = newDutyDayDate
        val dateInMilliseconds = date.value?.time
        // Einen Tag in Millisekunden subtrahieren:
        val newDateInMilliseconds = dateInMilliseconds?.minus(DAY_IN_MILLISECONDS)
        val newDate = newDateInMilliseconds?.let { Date(it) }
        _newDutyDayDate.value = newDate
    }

    fun increaseDay() {
        val date = newDutyDayDate
        val dateInMilliseconds = date.value?.time
        // Einen Tag in Millisekunden addieren:
        val newDateInMilliseconds = dateInMilliseconds?.plus(DAY_IN_MILLISECONDS)
        val newDate = newDateInMilliseconds?.let { Date(it) }
        _newDutyDayDate.value = newDate
    }

}