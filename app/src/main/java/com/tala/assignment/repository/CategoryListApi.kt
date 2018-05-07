package com.tala.assignment.repository

import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.BaseRequestHandler
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.CategoryModel
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel
import com.tala.assignment.utils.Constants
import retrofit2.Call

class CategoryListApi(val latlong : String, val talaNetworkService: TalaNetworkService): BaseRequestHandler<CategoryModel.Response>() {

    override fun makeRequest(): Call<CategoryModel.Response> {
        return talaNetworkService.getCategories(latlong,
                Constants.version(),
                Constants.clientId(),
                Constants.clientSecret())
    }

    fun getCategories() : LiveData<DataWrapper<CategoryModel.Response>> = doRequest()
}