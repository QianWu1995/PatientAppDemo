package com.qianwu.patientbooking.di.component

import android.app.Application
import android.content.Context
import com.qianwu.patientbooking.data.dao.PatientsDao
import com.qianwu.patientbooking.data.dao.VisitsDao
import com.qianwu.patientbooking.di.module.AppModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun getRetrofit(): Retrofit
    fun getApplicationContext(): Context
    fun getPatientDao(): PatientsDao
    fun getVisitDao(): VisitsDao

    fun inject(app: Application)
}