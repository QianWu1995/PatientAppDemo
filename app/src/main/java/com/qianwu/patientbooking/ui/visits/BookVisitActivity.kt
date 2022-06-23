package com.qianwu.patientbooking.ui.visits

import android.R
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.qianwu.patientbooking.MainApplication
import com.qianwu.patientbooking.databinding.ActivityBookVisitBinding
import com.qianwu.patientbooking.di.component.DaggerViewModelComponent
import com.qianwu.patientbooking.domain.entity.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookVisitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookVisitBinding

    private lateinit var availableServiceList: List<Service>
    private lateinit var zipCode: String

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var bookVisitsViewModel: BookVisitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookVisitBinding.inflate(layoutInflater)

        DaggerViewModelComponent.builder()
            .applicationComponent((applicationContext as MainApplication).appComponent)
            .build()
            .inject(this)

        setContentView(binding.root)
        availableServiceList = intent.getSerializableExtra("list") as List<Service>
        bookVisitsViewModel.initData(availableServiceList.map { it.id })
        zipCode = intent.getStringExtra("zip_code")!!
        binding.progressBar.animate()

        val serviceArrayAdapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, availableServiceList.map {
            it.name
        })

        binding.serviceSpinner.adapter = serviceArrayAdapter

        initData()

        bindUI()
    }

    private fun bindUI() {
        binding.bookVisit.setOnClickListener {
            bookVisitsViewModel.bookVisit().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        AlertDialog.Builder(this)
                            .setTitle("Your Book is complete!")
                            .setMessage("You have booked a visit at " + it.visitDatetime)
                            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                                finish()
                            })
                            .setIcon(R.drawable.ic_menu_save)
                            .show()
                    },
                    onError = {
                        //TODO::Show error toast
                    }
                )
        }

        binding.serviceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                bookVisitsViewModel.currentServiceIdIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.patientSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                bookVisitsViewModel.currentSelectedPatientIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.timeSlotSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                bookVisitsViewModel.currentSlectedTimeSlotIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initData() {

        bookVisitsViewModel.getAllPatients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onError = {

                },
                onSuccess = {
                    val arrayAdapter =
                        ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, it.map { it.firstName + " " + it.lastName })
                    binding.patientSpinner.adapter = arrayAdapter
                }
            ).addTo(compositeDisposable)

        bookVisitsViewModel.getAvailableTimesForServiceWithZipCode(
            availableServiceList.map { it.id!! }, zipCode
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onError = {
                          //TODO:: show error
                },
                onSuccess = {
                    binding.progressBar.clearAnimation()
                    binding.formGroup.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE

                    val arrayAdapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, it)
                    binding.timeSlotSpinner.adapter = arrayAdapter
                }
            ).addTo(compositeDisposable)
    }
}