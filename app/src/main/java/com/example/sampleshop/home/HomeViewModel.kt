package com.example.sampleshop.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sampleshop.helpers.OfferParser
import com.example.sampleshop.model.OfferItem

class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    val listOfOffers = MutableLiveData(emptyList<OfferItem>())

    private val context: Context
        get() = application.applicationContext
    init {
        listOfOffers.value = OfferParser.parseJson(context)
    }
}