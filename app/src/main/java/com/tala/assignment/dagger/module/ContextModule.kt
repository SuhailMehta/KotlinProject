package com.tala.assignment.dagger.module

import android.content.Context
import com.tala.assignment.dagger.interfaces.ApplicationContext
import com.tala.assignment.dagger.interfaces.TalaApplicationScope
import dagger.Module
import dagger.Provides

@Module
open class ContextModule internal constructor(private val context: Context){

    @Provides
    @ApplicationContext
    @TalaApplicationScope
    fun context(): Context = context.applicationContext

}