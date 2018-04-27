package com.tala.assignment.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TalaNetworkService {

    @GET("")
    fun getVenueDetails(@Query("a") a : Int) : Call<Any>
}