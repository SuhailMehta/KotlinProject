package com.tala.assignment.data.network

import com.tala.assignment.data.network.model.CategoryModel
import com.tala.assignment.data.network.model.VenueListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TalaNetworkService {

    @GET("venues/search")
    fun getVenueDetails(@Query("ll") ll: String,
                        @Query("categoryId") categoryId: String,
                        @Query("v") version: String,
                        @Query("client_id") client_id: String,
                        @Query("client_secret") client_secret: String): Call<VenueListModel.Response>

    @GET("venues/categories")
    fun getCategories(@Query("ll") ll: String,
                      @Query("v") version: String,
                      @Query("client_id") client_id: String,
                      @Query("client_secret") client_secret: String): Call<CategoryModel.Response>
}