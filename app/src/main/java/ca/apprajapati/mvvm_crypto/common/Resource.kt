package ca.apprajapati.mvvm_crypto.common

sealed class Resource<T : Any> {
    class Loading<T: Any>(val data : T ?= null) : Resource<T>()
    class Success<T: Any>(val data: T): Resource<T>()
    class Error<T: Any>(val data: T? = null, val message: String) : Resource<T>()
}