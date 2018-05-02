package com.tala.assignment.repository

import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.BaseRequestHandler
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.utils.Constants
import com.tala.assignment.viewmodel.VenueListViewModel
import retrofit2.Call
import javax.inject.Inject

class VenueListApi(val latlong : String, val talaNetworkService: TalaNetworkService): BaseRequestHandler<VenueListViewModel>() {

    override fun makeRequest(): Call<VenueListViewModel> {
        return talaNetworkService.getVenueDetails(latlong,
                Constants.version(),
                Constants.clientId(),
                Constants.clientSecret())
    }

    fun getVenues() : LiveData<DataWrapper<VenueListViewModel>> = doRequest()
}