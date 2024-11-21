package com.marvel.app.common.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull


class BaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Get the current base URL
        val citiesBaseUrl = "https://dev-orcas.s3.eu-west-1.amazonaws.com"
        val forecastBaseUrl = "https://api.openweathermap.org"

        val newBaseUrl:String = if (chain.request().url.toString().contains("/uploads/cities.json"))
            citiesBaseUrl
        else
            forecastBaseUrl
        // Parse the new base URL
        val parsedNewBaseUrl: HttpUrl = newBaseUrl?.toHttpUrlOrNull()
            ?: throw IllegalArgumentException("Invalid base URL: $newBaseUrl")

        // Rebuild the URL with the new base URL
        val newUrl = originalUrl.newBuilder()
            .scheme(parsedNewBaseUrl.scheme)
            .host(parsedNewBaseUrl.host)
            .port(parsedNewBaseUrl.port)
            .build()

        // Create a new request with the updated URL
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}