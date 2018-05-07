package com.tala.assignment.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tala.assignment.R
import com.tala.assignment.data.network.model.CategoryModel
import com.tala.assignment.data.network.model.VenueListModel

class VenueListAdapter<T>(private var venues: List<T>,
                          private val picasso: Picasso, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var progress: Boolean = false

    companion object {
        private const val PROGRESS_TYPE: Int = 1001
        private const val DATA_TYPE: Int = 1002
        private const val IMAGE_DIMENTIONS: Int = 88 // Four square Api specific
    }

    fun setData(venues: List<T>) {
        this.venues = venues
    }

    fun setProgress(progress: Boolean) {
        this.progress = progress
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is VenueListAdapter.ViewHolder) {

            val data = venues[position]
            if (data is VenueListModel.VenueRow) {

                if (data.categories!!.size > 0) {
                    val imageUrlBase = data.categories[0].icon
                    val imageUrl = "${imageUrlBase.prefix}$IMAGE_DIMENTIONS${imageUrlBase.suffix}"
                    picasso.load(imageUrl).into(holder.logo)
                } else {
                    picasso.load(R.drawable.placeholder).fit().into(holder.logo)
                }
                holder.name.text = data.name

                holder.parent.setOnClickListener {
                    listener.onItemClick(data)
                }
            } else if (data is CategoryModel.Category) {

                val imageUrlBase = data.icon
                if (imageUrlBase != null) {
                    val imageUrl = "${imageUrlBase.prefix}$IMAGE_DIMENTIONS${imageUrlBase.suffix}"
                    picasso.load(imageUrl).into(holder.logo)
                } else {
                    picasso.load(R.drawable.placeholder).fit().into(holder.logo)
                }
                holder.name.text = data.name

                holder.parent.setOnClickListener {
                    val row: VenueListModel.VenueRow = VenueListModel.VenueRow(name = data.name,
                            id = data.id, contact = null, location = null, categories = null)
                    listener.onItemClick(row)
                }
            }

        } else if (holder is VenueListAdapter<*>.ViewHolderProgressBar) {

            holder.bind(progress)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PROGRESS_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.progress, parent, false)

            return ViewHolderProgressBar(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.venue_list_row, parent, false)

        return ViewHolder(view)

    }

    override fun getItemViewType(position: Int): Int {
        if (progress) {
            return PROGRESS_TYPE
        }

        return DATA_TYPE
    }

    override fun getItemCount(): Int {
        if (progress) {
            return 1
        }
        return venues.size
    }

    class ViewHolder : RecyclerView.ViewHolder {

        val logo: ImageView
        val name: TextView
        val parent: View

        constructor(itemView: View) : super(itemView) {

            logo = itemView.findViewById(R.id.logo) as ImageView
            name = itemView.findViewById(R.id.name) as TextView
            parent = itemView.findViewById(R.id.venue_row_parent)

        }

    }

    inner class ViewHolderProgressBar : RecyclerView.ViewHolder {

        private val progressBar: ProgressBar

        constructor(itemView: View) : super(itemView) {

            progressBar = itemView.findViewById(R.id.progressBar) as ProgressBar

        }

        fun bind(progress: Boolean) {

            if (progress)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE

        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: VenueListModel.VenueRow)
    }

}