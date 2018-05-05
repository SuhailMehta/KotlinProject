package com.tala.assignment.data.network

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import com.tala.assignment.data.network.model.DataWrapper
import retrofit2.Call
import retrofit2.Response


abstract class BaseRequestHandler<R> {
    protected abstract fun makeRequest(): Call<R>
    fun doRequest(): LiveData<DataWrapper<R>> {
        val liveData = MutableLiveData<DataWrapper<R>>()
        val dataWrapper = DataWrapper<R>(null,null)
        makeRequest().enqueue(object : ApiCallback<R>() {
            override fun handleResponseData(data: R?) {
                val tempData = dataWrapper.copy(consdata = data)
                liveData.value = tempData
            }

            override  fun handleError(response: Response<R>?) {
                val tempData = dataWrapper.copy(apiExceptions = ApiErrorHandler<R>().getErrorData(response))
                liveData.value = tempData
            }

            override fun handleException(t: Exception) {
                val tempData = dataWrapper.copy(apiExceptions = t)
                liveData.value = tempData
            }
        })
        return liveData
    }
}