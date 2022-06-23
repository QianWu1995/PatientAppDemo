package com.qianwu.patientbooking.data.repo

import com.qianwu.patientbooking.data.API.APIClient
import com.qianwu.patientbooking.data.dao.VisitsDao
import com.qianwu.patientbooking.domain.entity.Visit
import com.qianwu.patientbooking.domain.entity.VisitRequest
import io.reactivex.Single
import javax.inject.Inject

class VisitsRepo @Inject constructor(private val apiClient: APIClient, private val visitsDao: VisitsDao) {

    fun createVisit(visitRequest: VisitRequest) = apiClient.createVisit(visitRequest).flatMap {
        apiClient.getVisitById(it.visitId)
    }.flatMap {
        visitsDao.insert(it).blockingAwait()
        Single.just(it)
    }

    fun getVisitByIdFromRemote(id: String) = apiClient.getVisitById(id)

    fun getVisitByIdFromLocal(id: String) = visitsDao.getVisitById(id)

    fun getAllVisitsFromLocal() = visitsDao.getAllVisits()
}