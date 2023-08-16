package com.example.khidicontest.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khidicontest.R
import com.example.khidicontest.data.PlaceSource
import com.example.khidicontest.model.Place


class TourListAdapter(applicationContext: Context):
    RecyclerView.Adapter<TourListAdapter.PlaceListViewHolder>() {

    private lateinit var placeList: List<Place>

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            var list = PlaceSource.places
            println("-==============================")

            placeList = list
            println(placeList)
        }, 2000)


    }
    class PlaceListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.place_title)
        val address: TextView = view.findViewById(R.id.place_address)
        val tel: TextView = view.findViewById(R.id.place_tel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceListViewHolder {
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.stay_list, parent, false)

        return PlaceListViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: PlaceListViewHolder, position: Int) {
        val item = placeList[position]

        holder.title.text = item.title
        holder.address.text = item.address
        holder.tel.text = item.tel
    }
}