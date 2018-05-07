package com.tala.assignment.repository

import javax.inject.Singleton
import android.arch.lifecycle.MediatorLiveData
import com.tala.assignment.viewmodel.CategoryListViewModel
import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.CategoryModel
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.data.network.model.VenueListModel


@Singleton
class CategoryRepository {

    private lateinit var mObservableVenueList: MediatorLiveData<DataWrapper<CategoryListViewModel>>

    private object HOLDER { val INSTANCE = CategoryRepository()}

    companion object {
        val instance : CategoryRepository by lazy { HOLDER.INSTANCE }
    }

    /**
     * Get the list of venues from the server and get notified when the data changes.
     */
    fun getCategories(): LiveData<DataWrapper<CategoryListViewModel>> {
        return mObservableVenueList
    }

    //"44.25354982989273,37.537300533687116"
    fun loadCategories(latlong: String, talaNetworkService: TalaNetworkService): LiveData<DataWrapper<CategoryModel.Response>> {
        return CategoryListApi(latlong,talaNetworkService).getCategories()

    }

}