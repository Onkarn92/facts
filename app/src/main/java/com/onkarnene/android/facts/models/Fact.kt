/*
 * Created by Onkar Nene on 17/7/19 4:58 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.models

import com.google.gson.annotations.SerializedName
import com.onkarnene.android.facts.utilities.DESCRIPTION
import com.onkarnene.android.facts.utilities.IMAGE_HREF
import com.onkarnene.android.facts.utilities.TITLE
import org.parceler.Parcel
import org.parceler.Parcel.Serialization.BEAN
import org.parceler.ParcelConstructor

/**
 * Model class holding fact's data.
 */
@Parcel(BEAN)
data class Fact @ParcelConstructor constructor(
		@SerializedName(TITLE) var title: String?,
		
		@SerializedName(DESCRIPTION) var description: String?,
		
		@SerializedName(IMAGE_HREF) var imageHref: String?
)