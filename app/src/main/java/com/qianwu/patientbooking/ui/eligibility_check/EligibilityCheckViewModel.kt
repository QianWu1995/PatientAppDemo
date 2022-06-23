package com.qianwu.patientbooking.ui.eligibility_check

import android.util.Log
import androidx.lifecycle.ViewModel
import com.qianwu.patientbooking.data.repo.ServiceRepo
import com.qianwu.patientbooking.di.scope.ViewModelScope
import com.qianwu.patientbooking.domain.entity.Service
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@ViewModelScope
class EligibilityCheckViewModel @Inject constructor(val serviceRepo: ServiceRepo) : ViewModel() {

    val eligibilityCheckObservable: BehaviorSubject<Pair<ELIGIBILITY_STATE, List<Service>?>> = BehaviorSubject.create()

    fun checkEligibility(zipCode: String) {
        serviceRepo.getServiceByZipCode(zipCode).subscribeOn(Schedulers.io()).subscribeBy(
            onSuccess = {
                if (it.isEmpty()) {
                    eligibilityCheckObservable.onNext(Pair(ELIGIBILITY_STATE.SERVICE_NOT_PROVIDED, null))
                } else {
                    eligibilityCheckObservable.onNext(Pair(ELIGIBILITY_STATE.OK, it))
                }
            },
            //TODO there could be more error cases than no internet, needs to elaborate on this use case
            onError = {
                eligibilityCheckObservable.onNext(Pair(ELIGIBILITY_STATE.NO_INTERNET, null))
            }
        )
    }

    enum class ELIGIBILITY_STATE(){
        OK,
        NO_INTERNET,
        SERVICE_NOT_PROVIDED
    }
}