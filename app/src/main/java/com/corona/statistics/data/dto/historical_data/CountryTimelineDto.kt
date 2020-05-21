package com.corona.statistics.data.dto.historical_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryTimelineDto {

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("province")
    @Expose
    var province: String? = null

    @SerializedName("timeline")
    @Expose
    var timeline: Timeline? = null

}