package com.qianwu.patientbooking.data.repo

import com.qianwu.patientbooking.data.API.APIClient
import com.qianwu.patientbooking.data.dao.PatientsDao
import com.qianwu.patientbooking.domain.entity.Address
import com.qianwu.patientbooking.domain.entity.CreateAddressRequest
import com.qianwu.patientbooking.domain.entity.Patient
import io.reactivex.Single
import javax.inject.Inject

class PatientRepo @Inject constructor(val apiClient: APIClient, val patientsDao: PatientsDao) {

    fun getAllPatientFromLocal() = patientsDao.getAllPatients()

    // create new patient and after success, will assign remote id to the patient
    fun createNewPatient(patient: Patient) : Single<Patient> = apiClient.createPatient(patient).doAfterSuccess {
        storePatientToLocal(patient.copy(it.id))
    }.flatMap {
        Single.just(patient.copy(it.id))
    }

    fun createAddressForUserId(address: Address, patientId: String) = apiClient.createAddress(CreateAddressRequest(address, patientId))

    private fun storePatientToLocal(patient: Patient) = patientsDao.insert(patient)
}