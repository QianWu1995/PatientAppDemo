package com.qianwu.patientbooking.ui.visits

import androidx.lifecycle.ViewModel
import com.qianwu.patientbooking.data.repo.AvailableTimesRepo
import com.qianwu.patientbooking.data.repo.PatientRepo
import com.qianwu.patientbooking.data.repo.VisitsRepo
import com.qianwu.patientbooking.di.scope.ViewModelScope
import com.qianwu.patientbooking.domain.entity.Patient
import com.qianwu.patientbooking.domain.entity.VisitRequest
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@ViewModelScope
class BookVisitsViewModel @Inject constructor(private val availableTimesRepo: AvailableTimesRepo,
                                              private val patientsRepo: PatientRepo,
                                              private val visitsRepo: VisitsRepo) : ViewModel() {

    lateinit var patientsList : List<Patient>

    var currentSelectedPatientIndex = 0

    var currentServiceIdIndex = 0

    var currentSlectedTimeSlotIndex = 0

    lateinit var availableServiceIdList : List<String>

    lateinit var availableTimsSlots : List<String>

    val patientsObservable = BehaviorSubject.create<List<Patient>>()

    val availableTimeObservable = BehaviorSubject.create<List<String>>()

    fun initData(availableServiceId: List<String>){
        availableServiceIdList = availableServiceId
    }

    fun getAllPatients() = patientsRepo.getAllPatientFromLocal().doOnSuccess {
        patientsObservable.onNext(it)
        patientsList = it
    }

    fun getAvailableTimesForServiceWithZipCode(serviceIds: List<String>, zipCode: String) =
        availableTimesRepo.getFlattenedAvailableTimesForServiceWithZipCode(serviceIds, zipCode).doOnSuccess {
            availableTimeObservable.onNext(it)
            availableTimsSlots = it
        }


    //TODO:: Fix hardcoded address, requisitioningProviderId, providerNotes
    fun bookVisit() = visitsRepo.createVisit(
        VisitRequest(
        patientId = patientsList[currentSelectedPatientIndex].id,
        addressId = "9837cf78-6698-4c94-aeb1-8685749480a9",
        visitDatetime = availableTimsSlots[currentSlectedTimeSlotIndex],
        isSelfPay = true,
        serviceIds = listOf("956bc5db-ee6e-4115-b34a-b2bbcdc7d7c6")/*listOf(availableServiceIdList[currentServiceIdIndex])*/,
        requisitioningProviderId = "f4a84e6a-037d-482b-98ed-9e48b35436c0",
        providerNotes = "Patients is having trouble hearing"
    ))
}