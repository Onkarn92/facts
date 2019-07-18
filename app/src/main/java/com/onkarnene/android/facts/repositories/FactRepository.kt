/*
 * Created by Onkar Nene on 18/7/19 12:45 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.repositories

import androidx.lifecycle.MutableLiveData
import com.onkarnene.android.facts.interfaces.HttpEventTracker
import com.onkarnene.android.facts.models.FactResponse
import com.onkarnene.android.facts.routers.FactApiRouter
import okhttp3.ResponseBody

object FactRepository : HttpEventTracker<FactResponse> {
	
	private val factApiRouter: FactApiRouter = FactApiRouter(this)
	private val factLiveData: MutableLiveData<Triple<FactResponse?, String?, Throwable?>> = MutableLiveData()
	
	override fun onCallSuccess(response: FactResponse) {
		factLiveData.postValue(Triple(response, null, null))
	}
	
	override fun onCallFail(
			cause: String,
			throwable: Throwable,
			responseBody: ResponseBody?
	) {
		factLiveData.postValue(Triple(null, cause, throwable))
	}
	
	fun initCall() {
		factApiRouter.init()
	}
	
	fun stopCall() {
		factApiRouter.stop()
	}
	
	fun getFactLiveData(): MutableLiveData<Triple<FactResponse?, String?, Throwable?>> = factLiveData
}