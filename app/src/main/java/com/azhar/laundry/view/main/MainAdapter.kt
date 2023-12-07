package com.azhar.laundry.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.laundry.R
import com.azhar.laundry.model.nearby.ModelResults
import com.azhar.laundry.view.main.MainAdapter.MainViewHolder

class MainAdapter(var context: Context) : RecyclerView.Adapter<MainViewHolder>() {
    var modelResultArrayList = ArrayList<ModelResults>()
    fun setLocationAdapter(items: ArrayList<ModelResults>?) {
        modelResultArrayList.clear()
        modelResultArrayList.addAll(items!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_rekomendasi, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val modelResult = modelResultArrayList[position]

        //set rating
        val newValue = modelResult.rating.toFloat()
        holder.ratingBar.numStars = 5
        holder.ratingBar.stepSize = 0.5.toFloat()
        holder.ratingBar.rating = newValue
        holder.tvNamaJalan.text = modelResult.vicinity
        holder.tvNamaLokasi.text = modelResult.name
        holder.tvRating.text = "(" + modelResult.rating + ")"
    }

    override fun getItemCount(): Int {
        return modelResultArrayList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearRute: LinearLayout
        var tvNamaJalan: TextView
        var tvNamaLokasi: TextView
        var tvRating: TextView
        var ratingBar: RatingBar

        init {
            linearRute = itemView.findViewById(R.id.linearRute)
            tvNamaJalan = itemView.findViewById(R.id.tvNamaJalan)
            tvNamaLokasi = itemView.findViewById(R.id.tvNamaLokasi)
            tvRating = itemView.findViewById(R.id.tvRating)
            ratingBar = itemView.findViewById(R.id.ratingBar)
        }
    }
}