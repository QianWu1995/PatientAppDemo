package com.qianwu.patientbooking.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qianwu.patientbooking.di.scope.ViewModelScope
import javax.inject.Inject
import javax.inject.Provider

@ViewModelScope
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}