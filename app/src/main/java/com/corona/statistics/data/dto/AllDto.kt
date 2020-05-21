package com.corona.statistics.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllDto {

    @SerializedName("updated")
    @Expose
    var updated: Long? = null

    @SerializedName("cases")
    @Expose
    var cases: Int? = null

    @SerializedName("deaths")
    @Expose
    var deaths: Int? = null

    @SerializedName("recovered")
    @Expose
    var recovered: Int? = null

    @SerializedName("active")
    @Expose
    var active: Int? = null

}