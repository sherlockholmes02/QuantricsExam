package com.quantrics.guidomia.data.converters

import androidx.room.TypeConverter
import com.quantrics.guidomia.utils.ConverterExtensions.fromJson
import com.quantrics.guidomia.utils.ConverterExtensions.toJson

class Converters {
    @TypeConverter
    fun stringToList(json: String?): List<String> = json?.fromJson() ?: listOf()

    @TypeConverter
    fun listToString(list: List<String?>?): String = list.toJson()
}