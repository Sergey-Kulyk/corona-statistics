package com.sergeykulyk.corona_statistics.data.dvo

data class CountryStatisticsDvo(
    val icon: String?,
    val name: String,
    val confirmed: String,
    val recovered: String,
    val deaths: String
)