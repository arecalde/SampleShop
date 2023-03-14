package com.example.sampleshop.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Helper {
    private val gson = Gson()

    private fun getSharedPreferences(context: Context) =
        context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

    private fun getIds(context: Context) : List<String> {
        val favoritesJson = getSharedPreferences(context).getString("favorites","")
        val typeToken = object: TypeToken<MutableList<String>>() {}.type
        return if (favoritesJson.isNullOrEmpty()) {
            listOf()
        } else {
            gson.fromJson<MutableList<String>>(favoritesJson, typeToken)
        }
    }

    fun isFavorite(context: Context, id: String): Boolean {
        return getIds(context).contains(id)
    }

    fun addId(context: Context, id: String) {
        val editor = getSharedPreferences(context).edit()
        val ids = getIds(context).toMutableList()
        ids.add(id)
        editor.putString("favorites", gson.toJson(ids))
        editor.apply()
    }

    fun removeId(context: Context, id: String) {
        val editor = getSharedPreferences(context).edit()
        val ids = getIds(context).toMutableList()
        ids.remove(id)
        editor.putString("favorites", gson.toJson(ids))
        editor.apply()
    }
}