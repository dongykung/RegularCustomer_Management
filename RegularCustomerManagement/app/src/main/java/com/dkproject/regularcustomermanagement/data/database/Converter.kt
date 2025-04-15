package com.dkproject.regularcustomermanagement.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import java.util.Date

class Converter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let { json.decodeFromString(it) }
    }

    @TypeConverter
    fun fromDateList(list: List<Date>?): String? {
        val timestampList = list?.map { it.time }
        return timestampList?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toDateList(value: String?): List<Date>? {
        return value?.let { data ->
            val timestampList = json.decodeFromString<List<Long>>(data)
            timestampList.map { Date(it) }
        }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }
}