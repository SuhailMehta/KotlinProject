package com.tala.assignment.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.repository.VenueRepository


open class VenueListViewModel: ViewModel() {

    private lateinit var mObservableVenueList: LiveData<DataWrapper<VenueListViewModel>>

    fun loadVenues(repository: VenueRepository, talaNetworkService: TalaNetworkService){
        mObservableVenueList = repository.loadVenues("", talaNetworkService)
    }
    /**
     * Expose the LiveData Venues query so the UI can observe it.
     */
    fun getVenues(): LiveData<DataWrapper<VenueListViewModel>> {
        return mObservableVenueList
    }
}