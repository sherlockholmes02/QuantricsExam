package com.quantrics.guidomia.utils

import com.google.gson.GsonBuilder

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
object ConverterExtensions {

    val gson = GsonBuilder()
        .create()

    fun Any?.toJson(): String =
        gson.toJson(this)

    inline fun <reified T> String.fromJson(): T =
        gson.fromJson(this, T::class.java)
}