package com.quantrics.guidomia.use_case

import androidx.lifecycle.LiveData
import com.quantrics.guidomia.data.daos.CarDao
import com.quantrics.guidomia.data.entities.Car

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
class FakeCarRepository : CarDao {
    private val cars: MutableList<Car> = mutableListOf()
    override fun get(): List<Car> {
        return cars
    }

    override fun getCars(): LiveData<List<Car>> {
        TODO("Not yet implemented")
    }

    override fun insertAllCars(cars: List<Car>) {
        this.cars.addAll(cars)
    }
}