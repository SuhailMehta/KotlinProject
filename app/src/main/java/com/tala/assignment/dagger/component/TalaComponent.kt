package com.tala.assignment.dagger.component

import com.squareup.picasso.Picasso
import com.tala.assignment.dagger.interfaces.TalaApplicationScope
import com.tala.assignment.dagger.module.ContextModule
import com.tala.assignment.dagger.module.PicassoModule
import com.tala.assignment.dagger.module.RetrofitModule
import com.tala.assignment.data.network.TalaNetworkService
import dagger.Component

@TalaApplicationScope
@Component(modules = arrayOf(RetrofitModule::class, PicassoModule::class))
interface TalaComponent {

    fun getTalaNetworkService(): TalaNetworkService
    fun getPicasso(): Picasso
}