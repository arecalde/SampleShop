package com.example.sampleshop.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.example.sampleshop.R
import com.example.sampleshop.helpers.Event
import com.example.sampleshop.helpers.Helper
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso


class OfferItem (
  @SerializedName("id"            ) var id           : String? = null,
  @SerializedName("url"           ) var url          : String? = null,
  @SerializedName("name"          ) var name         : String? = null,
  @SerializedName("description"   ) var description  : String? = null,
  @SerializedName("terms"         ) var terms        : String? = null,
  @SerializedName("current_value" ) var currentValue : String? = null
) {
  val launchDetails: Event<Unit> = Event(Unit)
  val drawable: MutableLiveData<Drawable> = MutableLiveData()
  fun launchDetailsFragment() = launchDetails.raiseEvent(Unit)

  fun changeDrawable(context: Context) {
    drawable.value = getDrawable(context)
  }
  private fun getDrawable(context: Context) = if (Helper.isFavorite(context, id.orEmpty())) {
    AppCompatResources.getDrawable(context, R.drawable.round_outline_favorite)
  } else {
    AppCompatResources.getDrawable(context, R.drawable.round_outline)
  }

  fun getImageUrl() = url ?: "https://png.pngtree.com/png-vector/20210604/ourmid/pngtree-gray-network-placeholder-png-image_3416659.jpg"
}

data class OfferDetail(val title: String, val value: String)

@BindingAdapter("imageUrl")
fun loadImage(view : View, url : String?){
  if (url.isNullOrEmpty()) return
  Picasso.get().load(url).into((view as ImageView))
  (view as? ImageView)?.clipToOutline = true
}