package ch.marcelfuchs.dutycalc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ch.marcelfuchs.dutycalc.util.DataConverters

@Database(entities = [Tour::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class DutyDayDatabase : RoomDatabase() {

    abstract fun tourDao(): DutyDayDao

    companion object {
        @Volatile
        private var INSTANCE: DutyDayDatabase? = null

        fun getDatabase(context: Context): DutyDayDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DutyDayDatabase::class.java,
                    "tour_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}