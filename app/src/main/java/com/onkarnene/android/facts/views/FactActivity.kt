/*
 * Created by Onkar Nene on 17/7/19 2:55 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.transaction
import com.onkarnene.android.facts.R
import com.onkarnene.android.facts.R.layout
import com.onkarnene.android.facts.views.fragments.FactFragment
import kotlinx.android.synthetic.main.activity_fact.*

class FactActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layout.activity_fact)
		supportFragmentManager.transaction {
			replace(R.id.factFrame, FactFragment())
		}
	}
}