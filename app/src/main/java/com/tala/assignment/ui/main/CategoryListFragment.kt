package com.tala.assignment.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tala.assignment.R
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.CategoryModel
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.repository.CategoryRepository
import com.tala.assignment.repository.VenueRepository
import com.tala.assignment.ui.adapter.VenueListAdapter
import com.tala.assignment.ui.customview.AutoFitRecycleView
import com.tala.assignment.viewmodel.CategoryListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


/**
 * A placeholder fragment containing a list of venues in grid layout.
 */
class CategoryListFragment : Fragment() {

    @Inject
    lateinit var talaNetworkService: TalaNetworkService

    @Inject
    lateinit var picasso: Picasso

    private lateinit var autoFitRecycleView: AutoFitRecycleView
    private var venueListAdapter: VenueListAdapter<CategoryModel.Category>? = null

    private val venues: ArrayList<CategoryModel.Category> = ArrayList()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)

        if (context is IActivityCommunication) {
            context.setTitle("Categories")
        } else {
            throw ClassCastException(activity.toString() + " must implement IActivityCommunication")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(CategoryListViewModel()::class.java)

        subscribeUi(viewModel)

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

    private fun subscribeUi(viewModel: CategoryListViewModel) {
        viewModel.getCategories(CategoryRepository.instance, talaNetworkService)!!.observe(this, Observer<DataWrapper<CategoryModel.Response>> {
            when {
                null != it?.data -> {

                    autoFitRecycleView.setColumnWidth(context?.resources?.getDimension(R.dimen.column_width)!!.toInt())

                    val categoryListModel: CategoryModel.Response = it.data

                    // Not handling load more case
                    venueListAdapter?.setData(categoryListModel.response.categories)
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
