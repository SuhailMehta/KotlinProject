package com.tala.assignment.dagger.module

import dagger.Provides
import android.app.Activity
import android.content.Context
import com.tala.assignment.dagger.interfaces.TalaApplicationScope
import dagger.Module
import javax.inject.Named


@Module
class ActivityModule(private val context: Activity) {

    @Named("activity_context")
    @TalaApplicationScope
    @Provides
    fun context(): Context {
        return context
    }
}