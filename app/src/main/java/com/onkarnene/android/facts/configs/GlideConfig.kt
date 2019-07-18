/*
 * Created by Onkar Nene on 18/7/19 12:43 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.configs

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * Custom configuration for glide to support [DecodeFormat.PREFER_ARGB_8888] format instead of [DecodeFormat.PREFER_RGB_565].
 */
@GlideModule
class GlideConfig : AppGlideModule() {
	
	override fun applyOptions(
			context: Context,
			builder: GlideBuilder
	) {
		super.applyOptions(context, builder)
		builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
	}
}