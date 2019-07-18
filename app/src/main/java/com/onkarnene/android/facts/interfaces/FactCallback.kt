/*
 * Created by Onkar Nene on 18/7/19 12:34 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.interfaces

import com.onkarnene.android.facts.models.Fact

interface FactCallback {
	
	/**
	 * Triggers when specific list item gets click event.
	 */
	fun onItemClick(fact: Fact)
}