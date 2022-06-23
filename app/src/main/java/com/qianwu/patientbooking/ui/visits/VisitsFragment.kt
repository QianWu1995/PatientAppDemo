package com.qianwu.patientbooking.ui.visits

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.qianwu.patientbooking.MainApplication
import com.qianwu.patientbooking.databinding.FragmentVisitsBinding
import com.qianwu.patientbooking.di.component.DaggerViewModelComponent
import com.qianwu.patientbooking.domain.entity.Service
import com.qianwu.patientbooking.ui.eligibility_check.EligibilityCheckDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.Serializable
import javax.inject.Inject

class VisitsFragment : Fragment(), StartBookVisitActivityListener {

    private lateinit var binding: FragmentVisitsBinding

    @Inject
    lateinit var visitsViewModel: VisitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerViewModelComponent.builder()
            .applicationComponent((activity?.application as MainApplication).appComponent)
            .build()
            .inject(this)

        binding = FragmentVisitsBinding.inflate(inflater, container, false)
        binding.addVisit.setOnClickListener {
            EligibilityCheckDialog(this).show(parentFragmentManager, "")
        }

        val root: View = binding.root

        return root
    }

    override fun onResume() {
        super.onResume()
        visitsViewModel.getAllVisits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {},
                onSuccess = {
                    if (it.isEmpty()) {
                        binding.textDefault.visibility = View.VISIBLE
                        binding.visitsListView.visibility = View.INVISIBLE
                    } else {
                        binding.textDefault.visibility = View.INVISIBLE
                        binding.visitsListView.visibility = View.VISIBLE
                        val adapter = VisitsRecyclerViewAdapter(it)
                        binding.visitsListView.adapter = adapter
                        binding.visitsListView.layoutManager = LinearLayoutManager(this.context)
                    }
                }
            )
    }

    //TODO:: Fix hardcoded name
    override fun startActivity(availableServices: List<Service>, zipCode: String) {
        startActivity(Intent(context, BookVisitActivity::class.java).apply {
            putExtra("list", availableServices as Serializable)
            putExtra("zip_code", zipCode)
        })
    }
}

interface StartBookVisitActivityListener {
    fun startActivity(availableServices: List<Service>, zipCode: String)
}
