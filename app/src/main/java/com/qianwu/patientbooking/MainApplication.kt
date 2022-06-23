package com.qianwu.patientbooking

import android.app.Application
import com.qianwu.patientbooking.data.database.AppDatabase
import com.qianwu.patientbooking.di.component.ApplicationComponent
import com.qianwu.patientbooking.di.component.DaggerApplicationComponent
import com.qianwu.patientbooking.di.module.AppModule

class MainApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)

        initDI()
    }

    private fun initDI(){
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}