package br.com.roquebuarque.bitcoinchart.domain

import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse
import br.com.roquebuarque.bitcoinchart.data.ChartStatistic
import br.com.roquebuarque.bitcoinchart.data.DatePoint
import io.reactivex.functions.Function
import java.sql.Timestamp
import java.util.*
import javax.inject.Inject

class BitcoinDataMapper @Inject constructor() : Function<BitcoinResponse, ChartStatistic> {

    override fun apply(reponse: BitcoinResponse) =
        with(reponse) {
            ChartStatistic(
                name = name,
                description = description,
                unit = unit,
                period = period,
                pointDate = values.map {
                    DatePoint(
                        dateTime = convertTimestampToDate(it.timestamp),
                        value = it.value
                    )
                }
            )
        }

    private fun convertTimestampToDate(timestamp: Long) = Date(Timestamp(timestamp).time)
}