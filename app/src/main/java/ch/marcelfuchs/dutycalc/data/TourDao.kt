package ch.marcelfuchs.dutycalc.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.marcelfuchs.dutycalc.model.Tour

@Dao
interface TourDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTour(tour: Tour)

    @Update
    suspend fun updateTour(tour: Tour)

    @Delete
    suspend fun deleteTour(tour: Tour)

    @Query("DELETE FROM tour_table")
    suspend fun deleteAllTours()

    @Query("SELECT * FROM tour_table ORDER BY day1 ASC")
    fun readAllData(): LiveData<List<Tour>>

}