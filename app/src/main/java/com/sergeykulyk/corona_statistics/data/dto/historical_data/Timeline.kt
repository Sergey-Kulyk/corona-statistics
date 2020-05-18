package com.sergeykulyk.corona_statistics.data.dto.historical_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Timeline {

    @SerializedName("cases")
    @Expose
    var casesDto: HashMap<String, Int>? = null

    @SerializedName("deaths")
    @Expose
    var deaths: HashMap<String, Int>? = null

    @SerializedName("recovered")
    @Expose
    var recovered: HashMap<String, Int>? = null

}