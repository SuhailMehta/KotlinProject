package com.tala.assignment.ui.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tala.assignment.R
import com.tala.assignment.data.network.TalaNetworkService
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.Nullable
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.repository.VenueRepository
import com.tala.assignment.viewmodel.VenueListViewModel


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    @Inject
    lateinit var talaNetworkService: TalaNetworkService

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
        val viewModel = ViewModelProviders.of(this).get(VenueListViewModel()::class.java)

        viewModel.loadVenues(VenueRepository.instance, talaNetworkService)
        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel: VenueListViewModel) {
        // Update the list when the data changes
        viewModel.getVenues().observe(this, Observer<DataWrapper<VenueListViewModel>> {  })
    }

}
