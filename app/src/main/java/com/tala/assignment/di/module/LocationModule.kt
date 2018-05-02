package com.tala.assignment.di.module

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tala.assignment.di.interfaces.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
open class LocationModule{

    @Provides
    fun fusedLocationClient(@ApplicationContext context: Context) : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

}