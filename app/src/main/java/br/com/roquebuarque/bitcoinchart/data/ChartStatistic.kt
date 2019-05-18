package br.com.roquebuarque.bitcoinchart.data

import java.util.*

data class ChartStatistic(
    val name: String,
    val description: String,
    val unit: String,
    val period: String,
    val pointDate: List<DatePoint>
)

data class DatePoint(
    val dateTime: Date,
    val value: Float
)