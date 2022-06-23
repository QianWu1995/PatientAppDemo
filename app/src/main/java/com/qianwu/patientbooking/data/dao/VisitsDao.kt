package com.qianwu.patientbooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qianwu.patientbooking.domain.entity.Visit
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface VisitsDao {
    @Query("SELECT * FROM visits")
    fun getAllVisits(): Single<List<Visit>>

    @Query("SELECT * FROM visits WHERE id = :id")
    fun getVisitById(id : String): Single<List<Visit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(visit: Visit) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(visit: List<Visit>) : Completable


}