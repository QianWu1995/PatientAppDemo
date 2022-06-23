package com.qianwu.patientbooking.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qianwu.patientbooking.R
import com.qianwu.patientbooking.domain.entity.Patient

class ProfileRecyclerViewAdapter(val patients : List<Patient>) : RecyclerView.Adapter<ProfileRecyclerViewAdapter.ProfileViewHolder>() {
    //TODO:: Use viewbinding instead of find view by id
    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val patientName = itemView.findViewById<TextView>(R.id.name)
        val patientDob = itemView.findViewById<TextView>(R.id.dob)

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val patientView = LayoutInflater.from(parent.context).inflate(R.layout.profile_list_item, parent, false)
        return ProfileViewHolder(patientView)
    }

    //TODO:: use string resource formatter
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = patients[position]
        holder.patientName.text = currentItem.firstName + currentItem.lastName
        holder.patientDob.text = currentItem.dob
    }

    override fun getItemCount(): Int = patients.size
}