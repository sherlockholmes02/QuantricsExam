package com.quantrics.guidomia.use_case

import com.quantrics.guidomia.data.daos.CarDao
import com.quantrics.guidomia.data.entities.Car

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
class GetCars(private val carDao: CarDao) {

    operator fun invoke(): List<Car> {
        return carDao.get()
    }
}