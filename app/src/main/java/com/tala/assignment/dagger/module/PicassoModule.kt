package com.tala.assignment.dagger.module

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.tala.assignment.dagger.interfaces.ApplicationContext
import com.tala.assignment.dagger.interfaces.TalaApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = arrayOf(OkHttpClientModule::class))
open class PicassoModule {

    @TalaApplicationScope
    @Provides
    fun picasso(@ApplicationContext context: Context , okHttp3Downloader: OkHttp3Downloader): Picasso = Picasso.Builder(context).downloader(okHttp3Downloader).build()

    @Provides
    fun okHttp3Downloader(@ApplicationContext okHttpClient: OkHttpClient) :OkHttp3Downloader = OkHttp3Downloader(okHttpClient)
}