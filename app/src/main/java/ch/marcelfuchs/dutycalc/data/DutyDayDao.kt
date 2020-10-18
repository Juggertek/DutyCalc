package ch.marcelfuchs.dutycalc.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.marcelfuchs.dutycalc.model.DutyDay

@Dao
interface DutyDayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDutyDay(dutyDay: DutyDay)

    @Update
    suspend fun updateDutyDay(dutyDay: DutyDay)

    @Delete
    suspend fun deleteDutyDay(dutyDay: DutyDay)

    @Query("DELETE FROM dutyDay_table")
    suspend fun deleteAllDutyDays()

    @Query("SELECT * FROM dutyDay_table ORDER BY date ASC")
    fun readAllData(): LiveData<List<DutyDay>>

}