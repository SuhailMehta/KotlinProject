package com.tala.assignment.ui.venuelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tala.assignment.R
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.repository.VenueRepository
import com.tala.assignment.ui.adapter.VenueListAdapter
import com.tala.assignment.ui.customview.AutoFitRecycleView
import com.tala.assignment.ui.main.IActivityCommunication
import com.tala.assignment.viewmodel.CategoryListViewModel
import com.tala.assignment.viewmodel.VenueListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


/**
 * A placeholder fragment containing a list of venues in grid layout.
 */
class VenueListFragment : Fragment() {

    companion object {
        fun title() = "TITLE"
        fun id() = "ID"
    }

    @Inject
    lateinit var talaNetworkService: TalaNetworkService

    @Inject
    lateinit var picasso: Picasso

    private lateinit var autoFitRecycleView: AutoFitRecycleView
    private var venueListAdapter: VenueListAdapter<VenueListModel.VenueRow>? = null
    private val venues: ArrayList<VenueListModel.VenueRow> = ArrayList()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args: Bundle? = arguments
        val title: String? = args?.getString(title())
        val id: String? = args?.getString(id())

        if (context is IActivityCommunication) {
            if (title != null) {
                (context as IActivityCommunication).setTitle(title)
            }
        } else {
            throw ClassCastException(activity.toString() + " must implement IActivityCommunication")
        }

        val viewModel = ViewModelProviders.of(this).get(VenueListViewModel()::class.java)

        subscribeUi(viewModel, id!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoFitRecycleView = auto_fit_recycle_view

        autoFitRecycleView.setHasFixedSize(true)

        venueListAdapter = VenueListAdapter(
                venues,
                picasso,
                object : VenueListAdapter.OnItemClickListener {
                    override fun onItemClick(item: VenueListModel.VenueRow) {
                        (context as IActivityCommunication).onClick(item)
                    }
                }
        )
        autoFitRecycleView.adapter = venueListAdapter
    }

    private fun subscribeUi(viewModel: VenueListViewModel, id: String) {
        viewModel.getVenues(id, VenueRepository.instance, talaNetworkService)!!.observe(this, Observer<DataWrapper<VenueListModel.Response>> {
            when {
                null != it?.data -> {

                    autoFitRecycleView.setColumnWidth(context?.resources?.getDimension(R.dimen.column_width)!!.toInt())

                    val venueListModel: VenueListModel.Response = it.data

                    // Not handling load more case
                    venueListAdapter?.setData(venueListModel.response.venues)
                    venueListAdapter?.setProgress(false)
                    venueListAdapter?.notifyDataSetChanged()

                }
                it?.progress!! -> {
                    venueListAdapter?.setProgress(it.progress)
                    venueListAdapter?.notifyDataSetChanged()
                }
                else -> {
                    // Handle Exception/ Network Error
                    venueListAdapter?.setProgress(false)
                    venueListAdapter?.notifyDataSetChanged()
                }
            }
        })
    }

}
