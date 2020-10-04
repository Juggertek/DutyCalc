package ch.marcelfuchs.dutycalc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ch.marcelfuchs.dutycalc.converters.Converters
import ch.marcelfuchs.dutycalc.model.Tour

@Database(entities = [Tour::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TourDatabase : RoomDatabase() {

    abstract fun userDao(): TourDao

    companion object {
        @Volatile
        private var INSTANCE: TourDatabase? = null

        fun getDatabase(context: Context): TourDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TourDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}