package br.com.roquebuarque.bitcoinchart.domain

import br.com.roquebuarque.bitcoinchart.data.Service
import br.com.roquebuarque.bitcoinchart.presentation.DashboardAction
import br.com.roquebuarque.bitcoinchart.presentation.DashboardActivity
import br.com.roquebuarque.bitcoinchart.presentation.DashboardState
import br.com.roquebuarque.bitcoinchart.presentation.StatisticResult
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrieveStatistic @Inject constructor(private val service: Service) {

    fun getChartInfo(): ObservableTransformer<DashboardAction, StatisticResult> =
        ObservableTransformer {
            service.fetchBitcoinStatistic()
                .map { data -> StatisticResult.Success(data) as StatisticResult }
                .onErrorReturn { error -> StatisticResult.Failure(error) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(StatisticResult.Loading)
        }
}