/*
 * Created by Onkar Nene on 17/7/19 3:02 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onkarnene.android.facts.R
import com.onkarnene.android.facts.adapters.FactListAdapter.ViewHolder
import com.onkarnene.android.facts.interfaces.FactCallback
import com.onkarnene.android.facts.models.Fact
import com.onkarnene.android.facts.utilities.NA
import kotlinx.android.synthetic.main.item_fact_list.view.*

/**
 * Responsible for holding and recycling the fact items in a list.
 */
class FactListAdapter(private val callback: FactCallback) : RecyclerView.Adapter<ViewHolder>() {
	
	private val items = mutableListOf<Fact>()
	
	/**
	 * Notifies the adapter to load the list again.
	 *
	 * @param facts to be added in list.
	 */
	fun setItems(facts: ArrayList<Fact>?) {
		facts?.removeAll {
			it.title.isNullOrBlank() && it.description.isNullOrBlank() && it.imageHref.isNullOrBlank()
		}
		if (facts != null) {
			for (item in facts) {
				if (!items.contains(item)) {
					items.add(item)
				}
			}
			notifyDataSetChanged()
		}
	}
	
	override fun onCreateViewHolder(
			parent: ViewGroup,
			viewType: Int
	): ViewHolder {
		val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fact_list, parent, false))
		holder.itemView.setOnClickListener {callback.onItemClick(holder.fact)}
		return holder
	}
	
	override fun getItemCount(): Int = items.size
	
	override fun onBindViewHolder(
			holder: ViewHolder,
			position: Int
	) {
		holder.setData(items[position])
	}
	
	/**
	 * Responsible for holding item related data.
	 */
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		
		lateinit var fact: Fact
		
		/**
		 * Inject respective [Fact] data in layout.
		 *
		 * @param fact to be loaded in layout.
		 */
		fun setData(fact: Fact) {
			this.fact = fact
			this.itemView.factTitleText.text = this.fact.title ?: NA
			this.itemView.factDescriptionText.text = this.fact.description ?: NA
			Glide.with(this.itemView.factImage).load(this.fact.imageHref).centerCrop().placeholder(R.drawable.loading_placeholder)
					.error(R.drawable.error_placeholder).into(this.itemView.factImage)
		}
	}
}