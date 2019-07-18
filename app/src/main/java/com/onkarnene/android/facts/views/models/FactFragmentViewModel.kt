/*
 * Created by Onkar Nene on 18/7/19 3:09 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.views.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onkarnene.android.facts.models.FactResponse
import com.onkarnene.android.facts.repositories.FactRepository

class FactFragmentViewModel : ViewModel() {
	
	fun getFacts() {
		FactRepository.initCall()
	}
	
	fun cancelRequest() {
		FactRepository.stopCall()
	}
	
	fun getFactLiveData(): MutableLiveData<Triple<FactResponse?, String?, Throwable?>> = FactRepository.getFactLiveData()
}