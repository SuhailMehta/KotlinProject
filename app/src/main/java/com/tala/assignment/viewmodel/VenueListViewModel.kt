package com.tala.assignment.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.repository.VenueRepository


class VenueListViewModel : ViewModel() {

    private var mObservableVenueList : LiveData<DataWrapper<VenueListModel.Response>>? = null

    private fun loadVenues(repository: VenueRepository, talaNetworkService: TalaNetworkService) {
        mObservableVenueList = repository.loadVenues("40.7484,-73.9857", talaNetworkService)
    }

    /**
     * Expose the LiveData Venues query so the UI can observe it.
     */
    fun getVenues(repository: VenueRepository, talaNetworkService: TalaNetworkService): LiveData<DataWrapper<VenueListModel.Response>>? {
        if (mObservableVenueList == null) {
            loadVenues(repository, talaNetworkService)
        }
        return mObservableVenueList
    }
}