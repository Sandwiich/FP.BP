package com.azhar.laundry.model.nearby

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelResults : Serializable {
    @SerializedName("geometry")
    var modelGeometry: ModelGeometry? = null

    @JvmField
    @SerializedName("name")
    var name: String? = null

    @JvmField
    @SerializedName("vicinity")
    var vicinity: String? = null

    @SerializedName("place_id")
    var placeId: String? = null

    @JvmField
    @SerializedName("rating")
    var rating = 0.0
}