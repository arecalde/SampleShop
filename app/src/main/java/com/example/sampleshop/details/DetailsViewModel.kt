package com.example.sampleshop.details

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleshop.helpers.Helper
import com.example.sampleshop.helpers.OfferParser
import com.example.sampleshop.model.OfferDetail
import com.squareup.picasso.Picasso

class DetailsViewModel(private val application: Application, private val id: String) : AndroidViewModel(application) {
    private val context: Context
        get() = application.applicationContext

    private val offers = OfferParser.parseJson(context)

    val offerDetails: MutableLiveData<List<OfferDetail>> = MutableLiveData()

    val favoriteButtonText = MutableLiveData("Favorite")
    val url = MutableLiveData("")

    init {
        changeFavoriteText()
        val offer = offers.find { it.id == id }
        offerDetails.value = listOf(
            OfferDetail("Name", offer?.name.orEmpty()),
            OfferDetail("Description", offer?.description.orEmpty()),
            OfferDetail("Terms", offer?.terms.orEmpty()),
            OfferDetail("Current Value", offer?.currentValue.orEmpty()),
        )
        url.value = offer?.url
    }

    fun favorite() {
        if (Helper.isFavorite(context, id)) {
            Helper.removeId(context, id)
        } else {
            Helper.addId(context, id)
        }
        changeFavoriteText()
    }

    private fun changeFavoriteText() {
        if (Helper.isFavorite(context, id)) {
            favoriteButtonText.value = "Unfavorite"
        } else {
            favoriteButtonText.value = "Favorite"
        }
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view : View, url : String?){
    if (url.isNullOrEmpty()) return
    Picasso.get().load(url).into((view as ImageView))
}

class DetailsViewModelFactory(private val application: Application, private val id: String):
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(application, id) as T
    }
}