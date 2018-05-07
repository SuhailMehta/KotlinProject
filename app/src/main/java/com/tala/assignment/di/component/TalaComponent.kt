package com.tala.assignment.di.component

import com.google.android.gms.location.FusedLocationProviderClient
import com.squareup.picasso.Picasso
import com.tala.assignment.ui.main.MainActivity
import com.tala.assignment.ui.main.CategoryListFragment
import com.tala.assignment.TalaApplication
import com.tala.assignment.di.interfaces.TalaApplicationScope
import com.tala.assignment.di.module.*
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.ui.venuelist.VenueListActivity
import com.tala.assignment.ui.venuelist.VenueListFragment
import dagger.Component
import dagger.Subcomponent
import dagger.android.AndroidInjector

@TalaApplicationScope
@Component(modules = arrayOf(RetrofitModule::class,
        PicassoModule::class, LocationModule::class,
        MainActivityModule::class, MainActivityFragmentModule::class,
        VenueListActivityModule::class, VenueListFragmentModule::class))
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
interface MainActivityFragmentSubComponent: AndroidInjector<CategoryListFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<CategoryListFragment>()
}

@Subcomponent
interface VenueActivitySubComponent: AndroidInjector<VenueListActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<VenueListActivity>()
}

@Subcomponent
interface VenueActivityFragmentSubComponent: AndroidInjector<VenueListFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<VenueListFragment>()
}