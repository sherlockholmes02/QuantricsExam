package com.quantrics.guidomia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
@Entity(tableName = "cars")
data class Car(
    @PrimaryKey
    var id: Int,
    var consList: MutableList<String>,
    var customerPrice: Double,
    var make: String,
    var marketPrice: Double,
    var model: String,
    var prosList: MutableList<String>,
    var rating: Int
)
