package com.example.khidicontest.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khidicontest.HospitalListActivity
import com.example.khidicontest.R

class SearchHospitalAdapter(applicationContext: Context) :
    RecyclerView.Adapter<SearchHospitalAdapter.SearchHospitalViewHolder>() {

    private val iconList: List<Int> = listOf(R.drawable.ic_face, R.drawable.ic_human, R.drawable.ic_sick, R.drawable.ic_skin, R.drawable.ic_hospital, R.drawable.ic_jci_hospital)
    private val list: List<String> = listOf("성형외과", "외과", "내과", "피부과", "종합병원", "JCI인증병원")

    class SearchHospitalViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.filter_image)
        val name: TextView = view.findViewById(R.id.filter_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHospitalViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.filter_list, parent, false)

        return SearchHospitalViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SearchHospitalViewHolder, position: Int) {
        holder.imageView.setImageResource(iconList[position])
        holder.name.text = list[position]
        holder.view.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, HospitalListActivity::class.java)
            intent.putExtra(HospitalListActivity.DEPARTMENT, holder.name.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}