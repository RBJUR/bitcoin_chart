package br.com.roquebuarque.bitcoinchart.domain

import br.com.roquebuarque.bitcoinchart.data.Service
import br.com.roquebuarque.bitcoinchart.presentation.StatisticResult
import io.reactivex.Observable
import javax.inject.Inject

class RetrieveStatistic @Inject constructor(private val service: Service) {

    fun getChartInfo(): Observable<StatisticResult> =
        service.fetchBitcoinStatistic()
            .map { data -> StatisticResult.Success(data) as StatisticResult }
            .onErrorReturn { error -> StatisticResult.Failure(error) }
            .startWith(StatisticResult.Loading)
}