package com.qianwu.patientbooking.di.module

import android.content.Context
import com.qianwu.patientbooking.MainApplication
import com.qianwu.patientbooking.data.API.ApiConstants
import com.qianwu.patientbooking.data.dao.PatientsDao
import com.qianwu.patientbooking.data.dao.VisitsDao
import com.qianwu.patientbooking.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import okhttp3.Headers
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val application: MainApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesOkhttpClient(): OkHttpClient = OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .headers(Headers.of(mutableMapOf(Pair("Authorization", ApiConstants.ApiToken))))
                .build()
            return chain.proceed(request)
        }
    }).build()

    @Provides
    @Singleton
    fun provideRetrofitHandle(ok_http_client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(ApiConstants.BASE_URL)
        .client(ok_http_client)
        .build()

    @Provides
    @Singleton
    fun provideAppDataBase(): AppDatabase = AppDatabase.getInstance()

    @Provides
    @Singleton
    fun providePatientDao(appDatabase: AppDatabase): PatientsDao = appDatabase.patientDao()

    @Provides
    @Singleton
    fun provideVisitsDao(appDatabase: AppDatabase): VisitsDao = appDatabase.visitsDao()


}