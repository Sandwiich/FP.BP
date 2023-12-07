package com.azhar.laundry.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.laundry.R

class MenuAdapter(var context: Context, var menuList: List<ModelMenu>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = menuList[position]
        holder.imageMenu.setImageResource(data.getImageDrawable())
        holder.tvTitle.text = data.getTvTitle()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvMenu: CardView
        var tvTitle: TextView
        var imageMenu: ImageView

        init {
            cvMenu = itemView.findViewById(R.id.cvMenu)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            imageMenu = itemView.findViewById(R.id.imageMenu)
            itemView.setOnClickListener { view: View? ->
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(menuList[position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(modelMenu: ModelMenu?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}

private fun ModelMenu.getImageDrawable(): Int {
    TODO("Not yet implemented")
}

private fun ModelMenu.getTvTitle(): CharSequence? {
    TODO("Not yet implemented")
}
