package com.tala.assignment

import android.app.Application
import com.tala.assignment.dagger.component.DaggerTalaComponent
import com.tala.assignment.dagger.component.TalaComponent
import com.tala.assignment.dagger.module.ContextModule

open class TalaApplication : Application() {

    private lateinit var talaComponent : TalaComponent

    override fun onCreate(){
        super.onCreate()

        talaComponent = DaggerTalaComponent.builder().contextModule(ContextModule(this)).build()


    }

}