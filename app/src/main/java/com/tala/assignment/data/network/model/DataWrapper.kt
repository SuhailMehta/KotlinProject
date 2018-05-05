package com.tala.assignment.data.network.model

data class DataWrapper<T>(private val apiExceptions: Exception?,private val consdata: T?) {

    val apiException: Exception? = apiExceptions

    val data: T? = consdata
}