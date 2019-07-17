/*
 * Created by Onkar Nene on 17/7/19 3:02 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.interfaces

import okhttp3.ResponseBody

interface HttpEventTracker<T> {
	
	/**
	 * Callback function.
	 * Call when current HTTP request executes successfully.
	 *
	 * @param response contains respective response model.
	 */
	fun onCallSuccess(response: T)
	
	/**
	 * Callback function.
	 * Calls when current HTTP request fails or response code is not in the range of [200..300).
	 *
	 * @param cause        of the request failure.
	 * @param throwable    contains cause of the failure.
	 * @param responseBody contains error body of response.
	 */
	fun onCallFail(
			cause: String,
			throwable: Throwable,
			responseBody: ResponseBody? = null
	)
}