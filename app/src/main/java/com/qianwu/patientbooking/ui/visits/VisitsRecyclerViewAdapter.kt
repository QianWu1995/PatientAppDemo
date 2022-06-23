package com.qianwu.patientbooking.ui.visits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qianwu.patientbooking.R
import com.qianwu.patientbooking.domain.entity.Visit

class VisitsRecyclerViewAdapter(val patients : List<Visit>) : RecyclerView.Adapter<VisitsRecyclerViewAdapter.VisitViewHolder>() {
    //TODO:: Use viewbinding instead of find view by id
    class VisitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val patientName = itemView.findViewById<TextView>(R.id.patient_name)
        val visitDate = itemView.findViewById<TextView>(R.id.visit_date)

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        val patientView = LayoutInflater.from(parent.context).inflate(R.layout.visit_list_item, parent, false)
        return VisitViewHolder(patientView)
    }

    //TODO:: use string resource formatter
    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val currentItem = patients[position]
        holder.patientName.text = currentItem.qhpNotes ?: "Mark"
        val list = currentItem.visitDatetime?.split("T", "-", ":")
        holder.visitDate.text = list?.let { it[1] + " - " + it[2] + " " + it[3] + ":" + it[4] }?: "2022-07-31 14:30"
    }

    override fun getItemCount(): Int = patients.size
}