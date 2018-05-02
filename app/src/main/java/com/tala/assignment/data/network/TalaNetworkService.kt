package com.tala.assignment.data.network

import com.tala.assignment.viewmodel.VenueListViewModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TalaNetworkService {

    @GET("venues/explore")
    fun getVenueDetails(@Query("ll") ll : String,
                        @Query("v") version: String,
                        @Query("client_id") client_id: String,
                        @Query("client_secret") client_secret: String) : Call<VenueListViewModel>
}