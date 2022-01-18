package com.quantrics.guidomia.main

import androidx.lifecycle.LiveData
import com.quantrics.guidomia.data.GuidomiaDatabase
import com.quantrics.guidomia.data.entities.Car
import com.quantrics.guidomia.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
class MainRepository(private val guidomiaDatabase: GuidomiaDatabase) {

    suspend fun getCars(): LiveData<List<Car>> {
        return withContext(Dispatchers.IO) {
            guidomiaDatabase.carDao().getCars()
        }
    }

    fun saveCars(cars: List<Car>) {
        Coroutines.inputOutput {
            guidomiaDatabase.carDao().insertAllCars(cars)
        }
    }
}