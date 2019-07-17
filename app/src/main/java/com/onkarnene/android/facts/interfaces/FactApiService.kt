/*
 * Created by Onkar Nene on 17/7/19 3:02 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.interfaces

import com.onkarnene.android.facts.models.Fact
import com.onkarnene.android.facts.models.FactResponse
import retrofit2.Call
import retrofit2.http.GET

interface FactApiService {
	
	/**
	 * HTTP [GET] method executor for [Fact] APIs.
	 * @return parsed JSON deserialize in [FactResponse] model.
	 */
	@GET("/s/2iodh4vg0eortkl/facts.json")
	fun getFacts(): Call<FactResponse>
}