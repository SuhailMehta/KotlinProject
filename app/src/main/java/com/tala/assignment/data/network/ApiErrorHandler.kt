package com.tala.assignment.data.network

import retrofit2.Response

class ApiErrorHandler<T> {

    //Modify accordingly, Keeping it simple now
    fun getErrorData(response: Response<T>?) = Exception("Server Error")
}