package com.tala.assignment.repository

import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.BaseRequestHandler
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.utils.Constants
import retrofit2.Call

class VenueListApi(val latlong : String, val talaNetworkService: TalaNetworkService): BaseRequestHandler<VenueListModel.Response>() {

    override fun makeRequest(): Call<VenueListModel.Response> {
        return talaNetworkService.getVenueDetails(latlong,
                Constants.version(),
                Constants.clientId(),
                Constants.clientSecret())
    }

    fun getVenues() : LiveData<DataWrapper<VenueListModel.Response>> = doRequest()
}