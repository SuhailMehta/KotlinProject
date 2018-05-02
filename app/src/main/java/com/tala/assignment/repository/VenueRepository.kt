package com.tala.assignment.repository

import javax.inject.Singleton
import android.arch.lifecycle.MediatorLiveData
import com.tala.assignment.viewmodel.VenueListViewModel
import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper


@Singleton
class VenueRepository private constructor() {

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
    fun loadVenues(latlong: String, talaNetworkService: TalaNetworkService): LiveData<DataWrapper<VenueListViewModel>> {
        return VenueListApi(latlong,talaNetworkService).getVenues()

    }

}