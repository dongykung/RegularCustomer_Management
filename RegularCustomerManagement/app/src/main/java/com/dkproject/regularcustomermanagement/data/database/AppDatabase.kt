package com.dkproject.regularcustomermanagement.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dkproject.regularcustomermanagement.data.dao.CustomerDao
import com.dkproject.regularcustomermanagement.data.model.CustomerEntity

@Database(entities = [CustomerEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}