package com.example.khidicontest.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.khidicontest.HospitalDetailActivity
import com.example.khidicontest.R
import com.example.khidicontest.data.DataSource
import com.example.khidicontest.data.JCISource
import com.example.khidicontest.model.Hospital
import com.opencsv.CSVReader
import java.io.FileReader


class HospitalListAdapter(applicationContext: Context, filterName: String, location: Int) :
    RecyclerView.Adapter<HospitalListAdapter.HospitalListViewHolder>() {

    private var filteredList: List<Hospital>
    private val locations = applicationContext.resources.getStringArray(R.array.location_array)

    init {
        var list = DataSource.hospitals
        var jci = JCISource.hospitals
        when (filterName) {
           "성형외과"  -> filteredList = list
                .filter { it.name.contains(filterName) }
           "내과" -> filteredList = list
               .filter  { it.name.contains(filterName) || it.type == "종합병원" }
           "외과" -> filteredList = list
               .filter { (it.name.contains(filterName) || it.type == "종합병원" ) && !it.name.contains("성형외과") }
           "피부과"  -> filteredList = list
                .filter { it.name.contains(filterName) }
           "종합병원" -> filteredList = list
               .filter { it.type == "병원" || it.type == "종합병원" }
           "JCI인증병원" -> filteredList = jci.sortedBy { it.name }
           else -> filteredList = list
        }
        if(location.equals(0)) {
            filteredList = filteredList
        } else {
            filteredList = filteredList.filter{ it.location == locations[location]}
        }
    }

    class HospitalListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.hospital_image)
        val title: TextView = view.findViewById(R.id.hospital_title)
        val address: TextView = view.findViewById(R.id.hospital_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalListViewHolder {
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.hospital_list, parent, false)

        return HospitalListViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HospitalListViewHolder, position: Int) {

        val item = filteredList[position]

        if(filteredList.isEmpty()) {
            return
        }

        holder.imageView.setImageResource(R.drawable.ic_hospital)
        if(item.name.length > 10) {
            holder.title.text = item.name
            holder.title.textScaleX = 0.9.toFloat()
        } else {
            holder.title.text = item.name
        }
        holder.address.text = item.address
        holder.view.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, HospitalDetailActivity::class.java)
            intent.putExtra(HospitalDetailActivity.HOSPITALNAME, holder.title.text.toString())
            intent.putExtra(HospitalDetailActivity.HOSPITALADDRESS, holder.address.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }


}