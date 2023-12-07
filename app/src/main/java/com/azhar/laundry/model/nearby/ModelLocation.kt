package com.azhar.laundry.model.nearby

import com.google.gson.annotations.SerializedName

class ModelLocation {
    @SerializedName("lat")
    var lat: Double? = null

    @SerializedName("lng")
    var lng: Double? = null
}