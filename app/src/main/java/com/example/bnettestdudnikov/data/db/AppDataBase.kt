package com.example.bnettestdudnikov.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bnettestdudnikov.data.model.Drug

@Database(
    entities = [Drug::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getDrugDBDao(): DrugDBDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDataBase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "DBDrug"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}