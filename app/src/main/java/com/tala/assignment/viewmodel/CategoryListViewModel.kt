package com.tala.assignment.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.tala.assignment.data.network.TalaNetworkService
import com.tala.assignment.data.network.model.CategoryModel
import com.tala.assignment.data.network.model.DataWrapper
import com.tala.assignment.repository.CategoryRepository


class CategoryListViewModel : ViewModel() {

    private var mObservableVenueList : LiveData<DataWrapper<CategoryModel.Response>>? = null

    private fun loadCategories(repository: CategoryRepository, talaNetworkService: TalaNetworkService) {
        mObservableVenueList = repository.loadCategories("40.7484,-73.9857", talaNetworkService)
    }

    /**
     * Expose the LiveData Venues query so the UI can observe it.
     */
    fun getCategories(repository: CategoryRepository, talaNetworkService: TalaNetworkService): LiveData<DataWrapper<CategoryModel.Response>>? {
        if (mObservableVenueList == null) {
            loadCategories(repository, talaNetworkService)
        }
        return mObservableVenueList
    }
}