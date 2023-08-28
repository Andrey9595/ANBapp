package ru.anb.myapplication.features.home.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.anb.myapplication.features.home.domain.model.UserPreview

class Convertor {
    private val gson = Gson()

    private inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun fromListJson(json: String): List<Long> {
//        val typeToken = TypeToken<List<Long>>().rawType
        return gson.fromJson(json)
    }


    @TypeConverter
    fun toListJson(list: List<Long>): String {
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromMapJson(json: String): Map<Long, UserPreview> {
//        val typeToken = TypeToken<List<Long>>().rawType
        return gson.fromJson(json)
    }

    @TypeConverter
    fun toMapJson(map: Map<Long, UserPreview>): String {
        return gson.toJson(map)
    }
}