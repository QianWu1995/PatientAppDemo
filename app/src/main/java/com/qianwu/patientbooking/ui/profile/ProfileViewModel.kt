package com.qianwu.patientbooking.ui.profile

import androidx.lifecycle.ViewModel
import com.qianwu.patientbooking.data.repo.PatientRepo
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val patientRepo: PatientRepo) : ViewModel() {
    fun getAllProfiles() = patientRepo.getAllPatientFromLocal()
}