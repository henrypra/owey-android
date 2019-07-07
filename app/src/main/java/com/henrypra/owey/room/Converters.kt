package com.henrypra.owey.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.henrypra.owey.model.Debt


class Converters {

    @TypeConverter
    fun listToJson(value: List<Debt>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Debt>? {
        val objects = Gson().fromJson(value, Array<Debt>::class.java) as Array<Debt>
        return objects.toList()
    }

    @TypeConverter
    fun stringListToJson(value: List<String>?): String {
        return Gson().toJson(value)

    }

    @TypeConverter
    fun jsonToStringList(value: String): List<String>? {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        return objects.toList()
    }

}