package com.example.employeedirectory.employee.models

sealed class Resource <out R> { data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val errorMessage: String, val data: T) : Resource<T>()
    data class Loading<out T>(val data: T) : Resource<T>()
    class Uninitiated: Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorMessage]"
            is Loading -> "Loading"
            is Uninitiated -> "Uninitiated"
            else -> "Uninitiated"
        }
    }
}