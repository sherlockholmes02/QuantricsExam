package com.quantrics.guidomia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.quantrics.guidomia.data.converters.Converters
import com.quantrics.guidomia.data.daos.CarDao
import com.quantrics.guidomia.data.entities.Car

@Database(
    version = 1,
    entities = [
        Car::class,
    ]
)
@TypeConverters(Converters::class)
abstract class GuidomiaDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private lateinit var INSTANCE: GuidomiaDatabase

        fun init(context: Context) {
            if (!::INSTANCE.isInitialized)
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    GuidomiaDatabase::class.java,
                    "guidomia_database"
                ).build()
        }

        fun getInstance() = INSTANCE

    }

    abstract fun carDao(): CarDao
}