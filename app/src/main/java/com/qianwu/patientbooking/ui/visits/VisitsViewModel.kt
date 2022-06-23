package com.qianwu.patientbooking.ui.visits

import androidx.lifecycle.ViewModel
import com.qianwu.patientbooking.data.repo.VisitsRepo
import javax.inject.Inject

class VisitsViewModel @Inject constructor(private val visitsRepo: VisitsRepo): ViewModel() {

    fun getAllVisits() = visitsRepo.getAllVisitsFromLocal()
}