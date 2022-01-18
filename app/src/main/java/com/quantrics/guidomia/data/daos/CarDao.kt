package com.quantrics.guidomia.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.quantrics.guidomia.data.entities.Car

@Dao
interface CarDao {

    @Query(
        """
            SELECT *
            FROM cars
        """
    )
    fun get(): List<Car>

    @Query("SELECT * FROM cars")
    fun getCars(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCars(cars: List<Car>)
}