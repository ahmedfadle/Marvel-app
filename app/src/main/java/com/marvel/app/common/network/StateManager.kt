package com.marvel.app.common.network


interface IViewState<T> {

    fun whichState(): CommonStates

    fun fetchData(): T?

    fun fetchError(): RetrofitException?
    fun fetchExceptionError(): Exception?


}


interface CommonStates

enum class CommonStatus : CommonStates {
    SUCCESS, LOADING, ERROR
}


data class Result<T>(
    val status: CommonStates,
    val data: T?,
    val errorException: RetrofitException?,
    val exceptionA: Exception?) : IViewState<T> {

    companion object {
        fun <T> success(data: T) = Result<T>(CommonStatus.SUCCESS, data, null,null)

        fun <T> success() = Result<T>(CommonStatus.SUCCESS, null, null,null)

        fun <T> error(retrofitException: RetrofitException) = Result<T>(CommonStatus.ERROR, null, retrofitException,null)
        fun <T> error(exception: Exception) = Result<T>(CommonStatus.ERROR, null, null,exception)

        fun <T> loading() = Result<T>(CommonStatus.LOADING, null, null,null)
    }

    override fun whichState(): CommonStates = status

    override fun fetchData(): T? = data

    override fun fetchError(): RetrofitException? = errorException
    override fun fetchExceptionError(): Exception? = exceptionA

}

