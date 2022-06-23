package com.qianwu.patientbooking.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qianwu.patientbooking.ui.ViewModelFactory
import com.qianwu.patientbooking.ui.eligibility_check.EligibilityCheckViewModel
import com.qianwu.patientbooking.ui.profile.AddProfileViewModel
import com.qianwu.patientbooking.ui.visits.BookVisitsViewModel
import com.qianwu.patientbooking.ui.profile.ProfileViewModel
import com.qianwu.patientbooking.ui.visits.VisitsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EligibilityCheckViewModel::class)
    internal abstract fun provideEligibleViewModel(viewModel: EligibilityCheckViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddProfileViewModel::class)
    internal abstract fun provideAddProfileViewModel(viewModel: AddProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun provideProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookVisitsViewModel::class)
    internal abstract fun provideBookVisitsViewModel(viewModel: BookVisitsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VisitsViewModel::class)
    internal abstract fun provideVisitsViewModel(viewModel: VisitsViewModel): ViewModel

}