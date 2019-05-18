package br.com.roquebuarque.bitcoinchart.data

import com.google.gson.annotations.SerializedName

data class BitcoinResponse(
    val name: String,
    val description: String,
    val unit: String,
    val period: String,
    val values: List<StatisticResponse>
)


data class StatisticResponse(
    @SerializedName("x") val timestamp: Long,
    @SerializedName("y") val value: Float
)