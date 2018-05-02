package com.tala.assignment.di.module

import android.content.Context
import com.tala.assignment.di.interfaces.ApplicationContext
import com.tala.assignment.di.interfaces.TalaApplicationScope
import dagger.Module
import dagger.Provides

@Module
open class ContextModule internal constructor(private val context: Context){

    @Provides
    @ApplicationContext
    @TalaApplicationScope
    fun context(): Context = context.applicationContext

}