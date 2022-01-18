package com.quantrics.guidomia.main

import androidx.lifecycle.ViewModel
import com.quantrics.guidomia.data.entities.Car
import com.quantrics.guidomia.utils.Coroutines
import com.quantrics.guidomia.utils.lazyDeferred

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val cars by lazyDeferred {
        mainRepository.getCars()
    }

    fun saveCarsToDatabase(cars: List<Car>) {
        Coroutines.inputOutput {
            mainRepository.saveCars(cars)
        }
    }
}