package com.qianwu.patientbooking.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qianwu.patientbooking.MainApplication
import com.qianwu.patientbooking.databinding.ActivityAddProfileBinding
import com.qianwu.patientbooking.di.component.DaggerViewModelComponent
import com.qianwu.patientbooking.domain.entity.Patient
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProfileBinding

    @Inject
    lateinit var addProfileViewModel: AddProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerViewModelComponent.builder()
            .applicationComponent((applicationContext as MainApplication).appComponent)
            .build()
            .inject(this)

        binding.createPatient.setOnClickListener {
            addProfileViewModel.addProfile(getPatientProfile())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                onSuccess = {
                    finish()
                },
                onError = {}
            )
        }
    }

    //TODO:: Fix hardcoded string
    private fun getPatientProfile() = Patient(id = "1",
        firstName = binding.firstName.text.toString(), lastName = binding.lastName.text.toString(), dob = binding.dob.text.toString(),
        email = binding.email.text.toString(), phone = binding.editTextPhone.text.toString()
    )
}