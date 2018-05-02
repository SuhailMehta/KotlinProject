package com.tala.assignment.di.module

import dagger.Provides
import android.app.Activity
import android.content.Context
import com.tala.assignment.di.interfaces.TalaApplicationScope
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