package br.com.roquebuarque.bitcoinchart.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.roquebuarque.bitcoinchart.domain.RetrieveStatistic
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val retrieveStatistic: RetrieveStatistic) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val state = MutableLiveData<DashboardState>()

    init {
        compositeDisposable.add(bindChartInfo())
    }

     fun bindChartInfo(): Disposable {
        return retrieveStatistic.getChartInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::updateChart)

     }

    private fun updateChart(dashState: DashboardState) {
        state.postValue(dashState)
    }
}