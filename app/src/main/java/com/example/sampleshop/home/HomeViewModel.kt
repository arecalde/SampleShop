package com.example.sampleshop.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sampleshop.model.OfferItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    val loading = MutableLiveData(false)
    val listOfOffers = MutableLiveData(emptyList<OfferItem>())

    private val context: Context
        get() = application.applicationContext
    init {
        val gson = Gson()
        val textBuilder = StringBuilder()
        val offerInputStream = context.assets.open("offers.json")

        BufferedReader(
            InputStreamReader(
                offerInputStream,
                Charset.forName(StandardCharsets.UTF_8.name())
            )
        ).use { reader ->
            var c: Int
            while (reader.read().also { c = it } != -1) {
                textBuilder.append(c.toChar())
            }
        }
        val itemType = object : TypeToken<List<OfferItem>>() {}.type
        val outputList: List<OfferItem> = gson.fromJson(textBuilder.toString(), itemType)
        listOfOffers.value = outputList
    }
}