package com.francis.movielisting.framework.db.converter

import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.francis.core.data.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Keep
class GenreConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromGenre(list: List<Genre>): String = gson.toJson(list)

    @TypeConverter
    fun toGenre(json: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type

        return try {
            gson.fromJson(json, listType)
        } catch (error: Throwable) {
            emptyList()
        }
    }
}