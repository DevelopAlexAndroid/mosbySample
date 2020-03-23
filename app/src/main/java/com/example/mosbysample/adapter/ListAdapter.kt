package com.example.mosbysample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mosbysample.R
import com.example.mosbysample.mosbyMVPLCE.DataModel
import kotlinx.android.synthetic.main.item_rec.view.*

class ListAdapter(val listData: ArrayList<DataModel>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rec, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dataItem: DataModel) {
            view.name.text = dataItem.name
            view.surname.text = dataItem.surname
        }
    }

}