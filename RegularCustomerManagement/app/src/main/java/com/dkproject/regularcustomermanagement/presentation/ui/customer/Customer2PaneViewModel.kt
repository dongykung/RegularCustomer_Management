package com.dkproject.regularcustomermanagement.presentation.ui.customer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val TOPIC_ID_KEY = "selectedCustomerId"
@HiltViewModel
class Customer2PaneViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {


    fun onCustomerClick(topicId: String?) {
        savedStateHandle[TOPIC_ID_KEY] = topicId
    }
}