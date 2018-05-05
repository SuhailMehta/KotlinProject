package com.tala.assignment.data.network.model

object CategoryModel{
    data class Response(val categories: ArrayList<Category>)
    data class Category(val categories: ArrayList<CategoryRow>)
    data class CategoryRow(val id: String, val name: String, val icon: Icon)
    data class Icon(val prefix: String, val suffix: String)
}