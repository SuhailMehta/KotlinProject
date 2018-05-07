package com.tala.assignment.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.viewmodel.VenueListViewModel
import javax.inject.Singleton


@Singleton
class VenueRepository {

    private lateinit var mObservableVenueList: MediatorLiveData<DataWrapper<VenueListViewModel>>

    private object HOLDER { val INSTANCE = VenueRepository()}

    companion object {
        val instance : VenueRepository by lazy { HOLDER.INSTANCE }
    }

    /**
     * Get the list of venues from the server and get notified when the data changes.
     */
    fun getVenues(): LiveData<DataWrapper<VenueListViewModel>> {
        return mObservableVenueList
    }

    //"44.25354982989273,37.537300533687116"
    fun loadVenues(latlong: String,id: String, talaNetworkService: TalaNetworkService): LiveData<DataWrapper<VenueListModel.Response>> {
        return VenueListApi(latlong, id, talaNetworkService).getVenues()

    }

}