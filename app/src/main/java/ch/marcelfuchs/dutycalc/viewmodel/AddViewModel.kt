package ch.marcelfuchs.dutycalc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ch.marcelfuchs.dutycalc.data.DutyDayDatabase
import ch.marcelfuchs.dutycalc.model.DutyDay
import ch.marcelfuchs.dutycalc.repository.DutyDayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import kotlin.math.roundToInt

// Konstante zur Addition/Subtraktion eines Tages.
private const val DAY_IN_MILLISECONDS: Long = 1000 * 60 * 60 * 24

class AddViewModel(application: Application) : AndroidViewModel(application) {

    val dutyDayBeingModified: DutyDay

    private val mIsEdit = false

    // Erzeugt aktuelles Datum
    private val _newDutyDayDate = MutableLiveData((Date(System.currentTimeMillis())))
    var newDutyDayDate: LiveData<Date> = _newDutyDayDate

    val dutyDayList: LiveData<List<DutyDay>>
    private val repository: DutyDayRepository

    init {
        val dutyDayDao = DutyDayDatabase.getDatabase(
            application
        ).dutyDayDao()
        repository = DutyDayRepository(dutyDayDao)
        dutyDayList = repository.tourList
    }

    fun addDutyDay(dutyDay: DutyDay) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDutyDay(dutyDay)
        }
    }

    fun updateDutyDay(dutyDay: DutyDay) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDutyDay(dutyDay)
        }
    }

    fun deleteDutyDay(dutyDay: DutyDay) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDutyDay(dutyDay)
        }
    }

    fun deleteAllDutyDays() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDutyDays()
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
        stbyStart: String,
        stbyEnd: String,
        show: String,
        dutyClosing: String
    ) {
        if (hasStby) {
            val stbyStartFloat = stringToFloat(stbyStart)
            val stbyEndFloat = stringToFloat(stbyEnd)
            // STBY@RL zählt nur zu 25%:
            val stbyDutyFloat = (stbyEndFloat - stbyStartFloat) / 4

            val showFloat = stringToFloat(show)
            val dutyClosingFloat = stringToFloat(dutyClosing)
            val dutyFloat = dutyClosingFloat - showFloat

            val totaldutyFloat = stbyDutyFloat + dutyFloat
        } else {
            val showFloat = stringToFloat(show)
            val dutyClosingFloat = stringToFloat(dutyClosing)
            val dutyFloat = dutyClosingFloat - showFloat

            val totaldutyFloat = dutyFloat
        }


//        val activeDutyDay = DutyDay(newDutyDayDate.value,)

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

    fun stringToFloat(string: String): Float {
        val hours = string.substringBefore(':').toFloat()
        val minutes = string.substringAfter(':').toFloat()
        return hours + minutes / 60
    }

    fun floatToString(float: Float): String {
        val hours = float.toInt()
        val hoursString = hours.toString().padStart(2, '0')
        val minutes = ((float - hours) * 60).roundToInt()
        val minutesString = minutes.toString().padStart(2, '0')
        return hoursString + ":" + minutesString
    }
}