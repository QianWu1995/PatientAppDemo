package com.qianwu.patientbooking.ui.eligibility_check

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.qianwu.patientbooking.MainApplication
import com.qianwu.patientbooking.databinding.DialogZipcodecheckBinding
import com.qianwu.patientbooking.di.component.DaggerViewModelComponent
import com.qianwu.patientbooking.domain.entity.Service
import com.qianwu.patientbooking.ui.MainActivity
import com.qianwu.patientbooking.ui.visits.StartBookVisitActivityListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EligibilityCheckDialog(val parentActivityListener : StartBookVisitActivityListener) : AppCompatDialogFragment(), ZipCodeDialogListener {

    @Inject
    lateinit var eligibilityViewModel: EligibilityCheckViewModel

    @Inject
    lateinit var applicationContext: Context

    private lateinit var binding: DialogZipcodecheckBinding
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogZipcodecheckBinding.inflate(LayoutInflater.from(context))

        val zipCode = binding.yourZipcode

        DaggerViewModelComponent.builder()
            .applicationComponent((((context as MainActivity).application) as MainApplication).appComponent)
            .build()
            .inject(this)

        compositeDisposable.add(
            eligibilityViewModel.eligibilityCheckObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        //showErrorToast()
                    },
                    onNext = {
                        when (it.first) {
                            EligibilityCheckViewModel.ELIGIBILITY_STATE.NO_INTERNET,
                            EligibilityCheckViewModel.ELIGIBILITY_STATE.SERVICE_NOT_PROVIDED-> {
                                showErrorToast(it.first)
                            }
                            EligibilityCheckViewModel.ELIGIBILITY_STATE.OK -> {
                                if(it.second == null){
                                    showErrorToast(it.first)
                                }
                                else{
                                    launchBookVisitActivity(it.second!!, zipCode.text.toString())
                                }
                            }
                        }
                    }
                )
        )


        //TODO:: fix hardcoded strings
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setTitle("Enter your zip code")
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .setPositiveButton("Ok") { _, _ ->
                this.applyTexts(zipCode.text.toString())
            }
            .create()
    }

    override fun applyTexts(zipCode: String?) {
        zipCode?.let {
            eligibilityViewModel.checkEligibility(it)
        }
    }

    private fun launchBookVisitActivity(availableServices: List<Service>, zipCode: String) {
        parentActivityListener.startActivity(availableServices, zipCode)
    }

    //TODO:: fix hardcode string and move this function to Util
    private fun showErrorToast(state: EligibilityCheckViewModel.ELIGIBILITY_STATE? = null) {
        val toast = Toast.makeText(
            applicationContext,
            when (state) {
                EligibilityCheckViewModel.ELIGIBILITY_STATE.NO_INTERNET -> "No internet, please retry"
                EligibilityCheckViewModel.ELIGIBILITY_STATE.SERVICE_NOT_PROVIDED -> "Service not provided in your region"
                else -> "Unknown Error"
            },
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show();
    }
}

interface ZipCodeDialogListener {
    fun applyTexts(zipCode: String?)
}