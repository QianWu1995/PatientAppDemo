package com.qianwu.patientbooking.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.qianwu.patientbooking.MainApplication
import com.qianwu.patientbooking.databinding.FragmentProfileBinding
import com.qianwu.patientbooking.di.component.DaggerViewModelComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        DaggerViewModelComponent.builder()
            .applicationComponent((activity?.application as MainApplication).appComponent)
            .build()
            .inject(this)

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.addPatientProfile.setOnClickListener {
            startActivity(Intent(context, AddProfileActivity::class.java))
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.getAllProfiles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if (it.isNotEmpty()) {
                        binding.textDashboard.visibility = View.INVISIBLE
                        binding.patientProfileListView.visibility = View.VISIBLE
                        val adapter = ProfileRecyclerViewAdapter(it)
                        binding.patientProfileListView.adapter = adapter
                        binding.patientProfileListView.layoutManager = LinearLayoutManager(this.context)
                    } else {
                        binding.textDashboard.visibility = View.VISIBLE
                        binding.patientProfileListView.visibility = View.INVISIBLE
                    }
                },
                onError = {
                    //TODO:: Handle case where fetching failed
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}