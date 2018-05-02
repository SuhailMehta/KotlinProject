package com.tala.assignment

import android.app.Activity
import android.app.Application
import com.tala.assignment.di.component.DaggerTalaComponent
import com.tala.assignment.di.component.TalaComponent
import com.tala.assignment.di.module.ContextModule
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


open class TalaApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    private lateinit var talaComponent : TalaComponent

    override fun onCreate(){
        super.onCreate()

        talaComponent = DaggerTalaComponent.builder().contextModule(ContextModule(this)).build()
        talaComponent.inject(this)
    }

    fun getTalaComponent(): TalaComponent {
        return talaComponent
    }

}