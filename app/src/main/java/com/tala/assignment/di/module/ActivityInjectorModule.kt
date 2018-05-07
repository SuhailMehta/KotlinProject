package com.tala.assignment.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.tala.assignment.ui.main.MainActivity
import com.tala.assignment.ui.main.CategoryListFragment
import com.tala.assignment.di.component.MainActivityFragmentSubComponent
import com.tala.assignment.di.component.MainActivitySubComponent
import com.tala.assignment.di.component.VenueActivityFragmentSubComponent
import com.tala.assignment.di.component.VenueActivitySubComponent
import com.tala.assignment.ui.venuelist.VenueListActivity
import com.tala.assignment.ui.venuelist.VenueListFragment
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
    @FragmentKey(CategoryListFragment::class)
    internal abstract fun bindMainActivityFragmentInjectorFactory(builder: MainActivityFragmentSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}

@Module(subcomponents = arrayOf(VenueActivitySubComponent::class))
abstract class VenueListActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(VenueListActivity::class)
    internal abstract fun bindVenueListActivityInjectorFactory(builder: VenueActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@Module(subcomponents = arrayOf(VenueActivityFragmentSubComponent::class))
abstract class VenueListFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(VenueListFragment::class)
    internal abstract fun bindVenueListFragmentInjectorFactory(builder: VenueActivityFragmentSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}