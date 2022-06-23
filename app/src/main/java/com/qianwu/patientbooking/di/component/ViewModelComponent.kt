package com.qianwu.patientbooking.di.component

import com.qianwu.patientbooking.di.module.ViewModelModule
import com.qianwu.patientbooking.di.scope.ViewModelScope
import com.qianwu.patientbooking.ui.eligibility_check.EligibilityCheckDialog
import com.qianwu.patientbooking.ui.profile.AddProfileActivity
import com.qianwu.patientbooking.ui.visits.BookVisitActivity
import com.qianwu.patientbooking.ui.profile.ProfileFragment
import com.qianwu.patientbooking.ui.visits.VisitsFragment
import dagger.Component

@ViewModelScope
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject(eligibilityCheckDialog: EligibilityCheckDialog)

    fun inject(addProfileActivity: AddProfileActivity)

    fun inject(profileFragment: ProfileFragment)

    fun inject(bookVisitActivity: BookVisitActivity)

    fun inject(visitsFragment: VisitsFragment)
}