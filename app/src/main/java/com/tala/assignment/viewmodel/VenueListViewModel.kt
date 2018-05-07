package com.tala.assignment.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.repository.VenueRepository

class VenueListViewModel : ViewModel() {

    private var mObservableVenueList : LiveData<DataWrapper<VenueListModel.Response>>? = null

    private fun loadVenues(id: String, repository: VenueRepository, talaNetworkService: TalaNetworkService) {
        mObservableVenueList = repository.loadVenues("40.7484,-73.9857",id, talaNetworkService)
    }

    /**
     * Expose the LiveData Venues query so the UI can observe it.
     */
    fun getVenues(id: String, repository: VenueRepository, talaNetworkService: TalaNetworkService): LiveData<DataWrapper<VenueListModel.Response>>? {
        if (mObservableVenueList == null) {
            loadVenues(id, repository, talaNetworkService)
        }
        return mObservableVenueList
    }
}