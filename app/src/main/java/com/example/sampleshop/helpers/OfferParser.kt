package com.example.sampleshop.helpers

import android.content.Context
import com.example.sampleshop.model.OfferItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


object OfferParser {
    fun parseJson(context: Context): List<OfferItem> {
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
        return gson.fromJson(textBuilder.toString(), itemType)
    }
}