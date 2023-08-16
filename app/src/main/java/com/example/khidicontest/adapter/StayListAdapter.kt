package com.example.khidicontest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khidicontest.R
import com.example.khidicontest.data.StaySource
import com.example.khidicontest.model.Stay

class StayListAdapter(applicationContext: Context):
    RecyclerView.Adapter<StayListAdapter.StayListViewHolder>() {

    private var stayList: List<Stay>

    init {
        var importStayList = StaySource.stay
        stayList = importStayList
    }


    class StayListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.place_title)
        val address: TextView = view.findViewById(R.id.place_address)
        val tel: TextView = view.findViewById(R.id.place_tel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StayListViewHolder {
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.stay_list, parent, false)

        return StayListViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return stayList.size
    }

    override fun onBindViewHolder(holder: StayListViewHolder, position: Int) {
        val item = stayList[position]

        holder.title.text = item.title
        holder.address.text = item.address
        holder.tel.text = item.tel
    }
}