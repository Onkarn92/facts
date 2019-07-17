/*
 * Created by Onkar Nene on 17/7/19 4:59 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.models

import com.google.gson.annotations.SerializedName
import com.onkarnene.android.facts.utilities.ROWS
import com.onkarnene.android.facts.utilities.TITLE
import org.parceler.Parcel
import org.parceler.Parcel.Serialization.BEAN
import org.parceler.ParcelConstructor

/**
 * Model class holding [Fact] API response.
 */
@Parcel(BEAN)
data class FactResponse @ParcelConstructor constructor(
		@SerializedName(TITLE) var title: String? = null,
		
		@SerializedName(ROWS) var rows: ArrayList<Fact> = arrayListOf()
)