package com.tala.assignment.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.tala.assignment.ui.main.MainActivity
import com.tala.assignment.ui.main.MainActivityFragment
import com.tala.assignment.di.component.MainActivityFragmentSubComponent
import com.tala.assignment.di.component.MainActivitySubComponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.support.FragmentKey


@Module(subcomponents = arrayOf(MainActivitySubComponent::class))
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@Module(subcomponents = arrayOf(MainActivityFragmentSubComponent::class))
abstract class MainActivityFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MainActivityFragment::class)
    internal abstract fun bindMainActivityFragmentInjectorFactory(builder: MainActivityFragmentSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}