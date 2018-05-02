package com.tala.assignment.data.network.model

data class DataWrapper<T>(private val apiExceptions: Exception?,private val consdata: T?) {

    private val apiException: Exception? = apiExceptions

    private val data: T? = consdata
}