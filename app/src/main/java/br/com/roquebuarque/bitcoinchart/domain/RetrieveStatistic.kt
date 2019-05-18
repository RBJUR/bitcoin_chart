package br.com.roquebuarque.bitcoinchart.domain

import br.com.roquebuarque.bitcoinchart.data.Service
import br.com.roquebuarque.bitcoinchart.presentation.DashboardState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RetrieveStatistic @Inject constructor(private val service: Service) {

    fun getChartInfo(): Observable<DashboardState> =
        service.fetchBitcoinStatistic()
            .map { items -> DashboardState.DataState(items) as  DashboardState }
            .onErrorReturn { t -> DashboardState.ErrorState(t) }
            .startWith(DashboardState.LoadingState)
            .concatWith(Observable.just(DashboardState.EndState))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


}