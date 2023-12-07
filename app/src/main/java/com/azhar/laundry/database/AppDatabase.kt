package com.azhar.laundry.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azhar.laundry.database.dao.LaundryDao
import com.azhar.laundry.model.ModelLaundry

@Database(entities = [ModelLaundry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun laundryDao(): LaundryDao?
}