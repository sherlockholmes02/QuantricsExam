package com.quantrics.guidomia.model

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
data class Car(
    var consList: List<String>,
    var customerPrice: Double,
    var make: String,
    var marketPrice: Double,
    var model: String,
    var prosList: List<String>,
    var rating: Int
)
