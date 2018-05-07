package com.tala.assignment.data.network.model

data class DataWrapper<T>(private val apiExceptions: Exception?, private val consData: T?,
                          val progress: Boolean) {

    val apiException: Exception? = apiExceptions

    val data: T? = consData

    val apiProgress = progress
}