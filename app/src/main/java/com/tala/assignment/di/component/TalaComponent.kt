package com.tala.assignment.di.component

import com.google.android.gms.location.FusedLocationProviderClient
import com.squareup.picasso.Picasso
import com.tala.assignment.ui.main.MainActivity
import com.tala.assignment.ui.main.MainActivityFragment
import com.tala.assignment.TalaApplication
import com.tala.assignment.di.interfaces.TalaApplicationScope
import com.tala.assignment.di.module.*
import com.tala.assignment.data.network.TalaNetworkService
import dagger.Component
import dagger.Subcomponent
import dagger.android.AndroidInjector

@TalaApplicationScope
@Component(modules = arrayOf(RetrofitModule::class, PicassoModule::class, LocationModule::class, MainActivityModule::class, MainActivityFragmentModule::class))
interface TalaComponent {

    fun getTalaNetworkService(): TalaNetworkService

    fun getPicasso(): Picasso

    fun getFusedLocationClient() : FusedLocationProviderClient

    fun inject(application: TalaApplication)

}

@Subcomponent
interface MainActivitySubComponent: AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>()
}

@Subcomponent
interface MainActivityFragmentSubComponent: AndroidInjector<MainActivityFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivityFragment>()
}