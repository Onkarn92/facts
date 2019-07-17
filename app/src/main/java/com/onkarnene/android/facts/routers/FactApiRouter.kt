/*
 * Created by Onkar Nene on 17/7/19 5:07 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.routers

import com.onkarnene.android.facts.interfaces.FactApiService
import com.onkarnene.android.facts.interfaces.HttpEventTracker
import com.onkarnene.android.facts.interfaces.HttpOperationCallback
import com.onkarnene.android.facts.models.Fact
import com.onkarnene.android.facts.models.FactResponse
import com.onkarnene.android.facts.networks.HttpOperationWrapper
import com.onkarnene.android.facts.utilities.NetworkUtils
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Responsible for building [Fact] API request and provide it to core network wrapper.
 */
class FactApiRouter(private val eventTracker: HttpEventTracker<FactResponse>) : HttpOperationCallback<FactResponse> {
	
	private lateinit var call: Call<FactResponse>
	
	/**
	 * [FactApiService] created by retrofit builder.
	 */
	private val factApiService: FactApiService by lazy {
		NetworkUtils.retrofitBuilder.build().create(FactApiService::class.java)
	}
	
	/**
	 * Initialize the [Fact] API call.
	 */
	fun init() {
		call = factApiService.getFacts()
		HttpOperationWrapper<FactResponse>().initCall(call, this)
	}
	
	/**
	 * Cancels the [Fact] API call if in process.
	 */
	fun stop() {
		if (this::call.isInitialized && call.isExecuted && !call.isCanceled) {
			call.cancel()
		}
	}
	
	override fun onResponse(
			call: Call<FactResponse>,
			result: FactResponse?,
			errorPair: Pair<String, Throwable>,
			errorBody: ResponseBody?
	) {
		when {
			result != null -> eventTracker.onCallSuccess(result)
			else -> eventTracker.onCallFail(errorPair.first, errorPair.second, errorBody)
		}
	}
}