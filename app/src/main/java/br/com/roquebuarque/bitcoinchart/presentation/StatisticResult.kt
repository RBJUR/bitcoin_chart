package br.com.roquebuarque.bitcoinchart.presentation

import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse

sealed class StatisticResult {

    object Loading: StatisticResult()
    data class Success(val data: BitcoinResponse): StatisticResult()
    data class Failure(val error:Throwable): StatisticResult()


}