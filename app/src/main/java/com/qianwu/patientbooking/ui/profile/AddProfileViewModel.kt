package com.qianwu.patientbooking.ui.profile

import androidx.lifecycle.ViewModel
import com.qianwu.patientbooking.data.repo.PatientRepo
import com.qianwu.patientbooking.domain.entity.Patient
import javax.inject.Inject

class AddProfileViewModel @Inject constructor(val patientRepo: PatientRepo) : ViewModel() {

    fun addProfile(patient: Patient) = patientRepo.createNewPatient(patient)

}