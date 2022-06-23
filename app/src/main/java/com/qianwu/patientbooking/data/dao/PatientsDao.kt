package com.qianwu.patientbooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qianwu.patientbooking.domain.entity.Patient
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PatientsDao {
    @Query("SELECT * FROM patients")
    fun getAllPatients(): Single<List<Patient>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(patient: Patient)
}