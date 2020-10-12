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
import java.sql.Time

// Konstante zur Addition/Subtraktion eines Tages.
private const val DAY_IN_MILLISECONDS: Long = 1000 * 60 * 60 * 24

class ViewModel(application: Application) : AndroidViewModel(application) {

    //Erstellen der LiveData Bindungen zur View (fragment_add.xml) mit null:

    // Erzeugt aktuelles Datum
    private val _newDutyDayDate = MutableLiveData((Date(System.currentTimeMillis())))
    var newDutyDayDate: LiveData<Date> = _newDutyDayDate

    private val _hasStby = MutableLiveData(false)
    var hasStby: LiveData<Boolean> = _hasStby

    private val _stbyStart = MutableLiveData<Time>(null)
    var stbyStart: LiveData<Time> = _stbyStart

    private val _stbyEnd = MutableLiveData<Time>(null)
    var stbyEnd: LiveData<Time> = _stbyEnd

    private val _show = MutableLiveData<Time>(null)
    var show: LiveData<Time> = _show

    private val _dutyClosing = MutableLiveData<Time>(null)
    var dutyClosing: LiveData<Time> = _dutyClosing

    private val _dutyTime = MutableLiveData<Float>(null)
    var dutyTime: LiveData<Float> = _dutyTime

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
        val dateInMilliseconds = newDutyDayDate.value!!.time
        // Einen Tag in Millisekunden subtrahieren:
        val newDateInMilliseconds = dateInMilliseconds.minus(DAY_IN_MILLISECONDS)
        val newDate = newDateInMilliseconds.let { Date(it) }
        _newDutyDayDate.value = newDate
    }

    fun increaseDay() {
        val dateInMilliseconds = newDutyDayDate.value!!.time
        // Einen Tag in Millisekunden addieren:
        val newDateInMilliseconds = dateInMilliseconds.plus(DAY_IN_MILLISECONDS)
        val newDate = Date(newDateInMilliseconds)
        _newDutyDayDate.value = newDate
    }

    fun insertDataToDatabase(
        hasStby: Boolean,
        stbyStart: Time,
        stbyEnd: Time,
        show: Time,
        dutyClosing: Time
    ) {
        if (hasStby) {
            val stbyDuty = Time(stbyEnd.time - stbyStart.time)
            val duty = Time(dutyClosing.time - show.time)
            val totalDuty = stbyDuty.time + duty.time
            _stbyStart.value = Time(totalDuty)
            _dutyTime.value = 61.75f


        } else {

        }

//        try {
//            if (inputCheck(firstName, lastName, age, dob)) {
//                // Create User Object
//        val dutyDay = DutyDay(
//            date,
//            standByStart,
//            standByEnd,
//            show,
//            closingTime
//        )

//        val dutyDay2 = dutyDay1.copy()
//        val dutyDay3 = dutyDay1.copy()
//        val dutyDay4 = dutyDay1.copy()
//        val dutyDay5 = dutyDay1.copy()
//        val dutyDay6 = dutyDay1.copy()
//        val dutyDay7 = dutyDay1.copy()
//        val time = Time.valueOf("23:55:00")

//        val tour = Tour(day1 = dutyDay)

//                    firstName,
//                    lastName,
//                    age.toIntOrNull(),
//                    if (dob.isEmpty()) null else Date.valueOf(dob)
//                )
        // Add Data to Database
//        mViewModel.addTour(tour)
//                Toast.makeText(
//                    requireContext(), "Successfully added!", Toast.LENGTH_LONG
//                ).show()
//                // Navigate Back
//                findNavController().navigate(R.id.action_addFragment_to_listFragment)
//            } else {
//                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
//                    .show()
//            }
//        } catch (e: IllegalArgumentException) {
//            Toast.makeText(requireContext(), "Please use format: YYYY-MM-DD", Toast.LENGTH_LONG)
//                .show()
//        }
    }
}