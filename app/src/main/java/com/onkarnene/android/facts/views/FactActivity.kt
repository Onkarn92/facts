/*
 * Created by Onkar Nene on 17/7/19 2:55 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onkarnene.android.facts.R.layout

class FactActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layout.activity_fact)
	}
}