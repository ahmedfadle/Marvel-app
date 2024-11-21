package com.marvel.app.common.network

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException

class NetworkUtils<T> {

     companion object {
         fun <T> error(throwable: Exception): IViewState<T> {

             when (throwable) {
                 is ResponseInterceptor.NoInternetConnection, is IOException ->
                     return Result.error(
                         RetrofitException.networkError(IOException(throwable))
                     )
                 is HttpException -> return Result.error(RetrofitException.httpError(throwable.response()))

                 /**
                  * handle json parsing exceptions
                  * */
                 is JsonParseException, is JsonSyntaxException, is JSONException, is JsonIOException ->
                     return Result.error(
                         RetrofitException.responseError(throwable)
                     )
                 /**
                  * handle realm db exceptions
                  * */
                 //is IllegalStateException
                 else -> return Result.error(RetrofitException.unexpectedError(throwable))
             }

         }

     }

}