package com.qianwu.patientbooking.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.qianwu.patientbooking.data.dao.PatientsDao
import com.qianwu.patientbooking.data.dao.VisitsDao
import com.qianwu.patientbooking.data.database.AppDatabase.Companion.ROOM_DATABASE_VERSION
import com.qianwu.patientbooking.domain.Converters
import com.qianwu.patientbooking.domain.entity.Patient
import com.qianwu.patientbooking.domain.entity.Visit

@Database(
    entities = [Visit::class, Patient::class],

    version = ROOM_DATABASE_VERSION
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun visitsDao(): VisitsDao
    abstract fun patientDao(): PatientsDao

    companion object {
        private const val DATABASE_NAME = "patient_app_db"

        // TODO:: Add migration script and increment DB version number when making changes to DB
        const val ROOM_DATABASE_VERSION = 1
        lateinit var database: AppDatabase

        @JvmStatic
        fun init(context: Context){
            database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): AppDatabase = database
    }
}