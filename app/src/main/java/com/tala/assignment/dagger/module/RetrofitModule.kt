package com.tala.assignment.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tala.assignment.dagger.interfaces.ApplicationContext
import com.tala.assignment.data.network.TalaNetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(ContextModule::class))
open class RetrofitModule {

    @Provides
    fun talaNetworkService(retrofit: Retrofit): TalaNetworkService = retrofit.create(TalaNetworkService::class.java)

    @Provides
    fun retrofit(@ApplicationContext okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}