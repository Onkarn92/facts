/*
 * Created by Onkar Nene on 17/7/19 3:40 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.utilities

import androidx.annotation.StringRes
import com.onkarnene.android.facts.App

object Utils {
	
	/**
	 * Returns a localized string from the application's default string table.
	 *
	 * @param id should be [StringRes] ID of the string.
	 *
	 * @return The string data associated with the resource, stripped of styled text information.
	 */
	fun getString(@StringRes id: Int, vararg args: Any = emptyArray()): String = App.getContext().getString(id, args)
}