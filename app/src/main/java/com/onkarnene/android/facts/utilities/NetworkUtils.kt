/*
 * Created by Onkar Nene on 17/7/19 3:01 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.utilities

import android.content.Context
import android.net.ConnectivityManager
import com.onkarnene.android.facts.App
import com.onkarnene.android.facts.R
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit.*

object NetworkUtils {
	
	const val CODE_NO_INTERNET: Int = 0
	private const val BASE_URL = "https://dl.dropboxusercontent.com/"
	private const val CODE_UNKNOWN: Int = -1
	private val HTTP_REQUEST_FAIL = Utils.getString(R.string.request_fail)
	private val BAD_REQUEST = Utils.getString(R.string.bad_request) to Throwable(Utils.getString(R.string.err_bad_request))
	private val UNAUTHORIZED = Utils.getString(R.string.user_unauthorized) to Throwable(Utils.getString(R.string.err_user_unauthorized))
	private val PAGE_NOT_FOUND = Utils.getString(R.string.page_not_found) to Throwable(Utils.getString(R.string.err_page_not_found))
	private val TIMEOUT = Utils.getString(R.string.request_timeout) to Throwable(Utils.getString(R.string.err_request_timeout))
	private val MAINTENANCE_BREAK = Utils.getString(R.string.maintenance_break) to Throwable(Utils.getString(R.string.err_maintenance_break))
	private val NO_INTERNET = HTTP_REQUEST_FAIL to Throwable(Utils.getString(R.string.err_no_internet))
	
	/**
	 * Creates single instance of retrofit's builder.
	 *
	 * @return retrofit's builder instance.
	 */
	val retrofitBuilder: Retrofit.Builder by lazy {
		val client = OkHttpClient.Builder().connectTimeout(20, SECONDS).readTimeout(20, SECONDS).build()
		Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client)
	}
	
	/**
	 * Check for response validity with following conditions:
	 * NotNull, Non-empty body, {[Response.isSuccessful]}
	 *
	 * @param response object to be examined.
	 *
	 * @return true if response is valid, otherwise false.
	 */
	fun isValidResponse(response: Response<*>): Boolean = response.isSuccessful && response.body() != null
	
	/**
	 * Provide human readable error messages.
	 *
	 * @return [Pair] of string cause and throwable (meaningful error message).
	 */
	fun getRequestFailReason(
			code: Int = CODE_UNKNOWN,
			throwable: Throwable? = null
	) = when (code) {
		CODE_NO_INTERNET -> NO_INTERNET
		HttpURLConnection.HTTP_BAD_REQUEST, HttpURLConnection.HTTP_BAD_METHOD, HttpURLConnection.HTTP_UNSUPPORTED_TYPE -> BAD_REQUEST
		HttpURLConnection.HTTP_UNAUTHORIZED -> UNAUTHORIZED
		HttpURLConnection.HTTP_NOT_FOUND, HttpURLConnection.HTTP_NOT_ACCEPTABLE -> PAGE_NOT_FOUND
		HttpURLConnection.HTTP_CLIENT_TIMEOUT, HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> TIMEOUT
		HttpURLConnection.HTTP_INTERNAL_ERROR, HttpURLConnection.HTTP_BAD_GATEWAY, HttpURLConnection.HTTP_UNAVAILABLE -> MAINTENANCE_BREAK
		else -> HTTP_REQUEST_FAIL to (throwable ?: Throwable(Utils.getString(R.string.err_something_wrong)))
	}
	
	/**
	 * Check for internet connection availability.
	 *
	 * @return true if device is currently connected to the internet (WiFi or Mobile Data), otherwise false.
	 */
	fun isNetworkAvailable(): Boolean {
		val networkInfo = (App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.activeNetworkInfo
		return networkInfo != null && networkInfo.isConnected
	}
}