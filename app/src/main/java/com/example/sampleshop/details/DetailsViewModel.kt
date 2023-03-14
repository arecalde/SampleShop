package com.example.sampleshop.details

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleshop.helpers.OfferParser
import com.example.sampleshop.model.OfferDetail

class DetailsViewModel(private val application: Application, private val id: String) : AndroidViewModel(application) {
    private val context: Context
        get() = application.applicationContext

    private val offers = OfferParser.parseJson(context)

    val offerDetails: MutableLiveData<List<OfferDetail>> = MutableLiveData()

    init {
        val offer = offers.find { it.id == id }
        offerDetails.value = listOf(
            OfferDetail("Name", offer?.name.orEmpty()),
            OfferDetail("Description", offer?.description.orEmpty()),
            OfferDetail("Terms", offer?.terms.orEmpty()),
            OfferDetail("Current Value", offer?.currentValue.orEmpty()),
        )
    }
}

class DetailsViewModelFactory(private val application: Application, private val id: String):
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(application, id) as T
    }
}