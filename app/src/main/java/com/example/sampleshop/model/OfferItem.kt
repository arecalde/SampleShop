package com.example.sampleshop.model

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.sampleshop.helpers.Event
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

  fun launchDetailsFragment() = launchDetails.raiseEvent(Unit)
  fun getImageUrl() = url ?: "https://png.pngtree.com/png-vector/20210604/ourmid/pngtree-gray-network-placeholder-png-image_3416659.jpg"
}

data class OfferDetail(val title: String, val value: String)

@BindingAdapter("imageUrl")
fun loadImage(view : View, url : String?){
  if (url.isNullOrEmpty()) return
  Picasso.get().load(url).into((view as ImageView))
  (view as? ImageView)?.clipToOutline = true
}