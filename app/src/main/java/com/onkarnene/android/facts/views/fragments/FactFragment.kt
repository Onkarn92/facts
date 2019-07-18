/*
 * Created by Onkar Nene on 17/7/19 2:59 PM
 *
 * Copyright (c) 2019 Onkar Nene. All rights reserved.
 */

package com.onkarnene.android.facts.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.onkarnene.android.facts.R
import com.onkarnene.android.facts.adapters.FactListAdapter
import com.onkarnene.android.facts.interfaces.FactCallback
import com.onkarnene.android.facts.models.Fact
import com.onkarnene.android.facts.models.FactResponse
import com.onkarnene.android.facts.utilities.NA
import com.onkarnene.android.facts.views.models.FactFragmentViewModel
import kotlinx.android.synthetic.main.fragment_fact.*
import kotlinx.android.synthetic.main.no_data_found.*

class FactFragment : Fragment(), FactCallback, Observer<Triple<FactResponse?, String?, Throwable?>> {
	
	private lateinit var factFragmentViewModel: FactFragmentViewModel
	
	override fun onCreateView(
			inflater: LayoutInflater,
			container: ViewGroup?,
			savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_fact, container, false)
	
	override fun onViewCreated(
			view: View,
			savedInstanceState: Bundle?
	) {
		super.onViewCreated(view, savedInstanceState)
		factFragmentViewModel = ViewModelProviders.of(this)[FactFragmentViewModel::class.java]
		factFragmentViewModel.getFactLiveData().observe(this, this)
		factRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
		factRecyclerView.adapter = FactListAdapter(this)
		factRefreshLayout.setOnRefreshListener(this::onRefresh)
	}
	
	override fun onResume() {
		super.onResume()
		onRefresh()
	}
	
	override fun onItemClick(fact: Fact) {
		// Handle item clicks.
	}
	
	override fun onChanged(t: Triple<FactResponse?, String?, Throwable?>?) {
		factRefreshLayout.isRefreshing = false
		when {
			t == null -> {
				factRecyclerView.visibility = View.GONE
				factNoDataLayout.visibility = View.VISIBLE
				noDataTitleText.text = getString(R.string.bad_request)
				noDataSubtitleText.text = getString(R.string.err_bad_request)
			}
			t.first != null -> {
				factRecyclerView.visibility = View.VISIBLE
				factNoDataLayout.visibility = View.GONE
				factToolbar.title = t.first?.title ?: NA
				(factRecyclerView.adapter as FactListAdapter).setItems(t.first?.rows)
			}
			t.second != null -> {
				factRecyclerView.visibility = View.GONE
				factNoDataLayout.visibility = View.VISIBLE
				noDataTitleText.text = t.second
				noDataSubtitleText.text = t.third?.localizedMessage ?: NA
			}
		}
	}
	
	fun onRefresh() {
		factRefreshLayout.isRefreshing = true
		factFragmentViewModel.getFacts()
	}
}