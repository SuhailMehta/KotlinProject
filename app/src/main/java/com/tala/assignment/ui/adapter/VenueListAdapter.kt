package com.tala.assignment.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tala.assignment.R
import com.tala.assignment.data.network.model.VenueListModel

class VenueListAdapter(private val venues: List<VenueListModel.VenueRow>,
                       private val picasso: Picasso, private val listener: OnItemClickListener) : RecyclerView.Adapter<VenueListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = venues[position]

        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.venue_list_row, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int = venues.size

    inner class ViewHolder : RecyclerView.ViewHolder {

        private val logo: ImageView
        private val name: TextView
        private val parent: View

        constructor(itemView: View) : super(itemView) {

            logo = itemView.findViewById(R.id.logo) as ImageView
            name = itemView.findViewById(R.id.name) as TextView
            parent = itemView.findViewById(R.id.venue_row_parent)

        }

        fun bind(data : VenueListModel.VenueRow){
            var imageUrl = ""

            if (data.categories.size > 0) {
                val imageUrlBase = data.categories[0].icon
                imageUrl = "${imageUrlBase.prefix}88${imageUrlBase.suffix}"
                picasso.load(imageUrl).into(logo)
            }

            name.text = data.name

            parent.setOnClickListener {
                listener.onItemClick(data)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: VenueListModel.VenueRow)
    }

}