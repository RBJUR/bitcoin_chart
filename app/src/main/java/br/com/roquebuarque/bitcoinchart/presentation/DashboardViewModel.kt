package br.com.roquebuarque.bitcoinchart.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.roquebuarque.bitcoinchart.domain.RetrieveStatistic
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val retrieveStatistic: RetrieveStatistic) : ViewModel() {

    private val publishSubject : PublishSubject<DashboardAction> = PublishSubject.create()
    private val compositeDisposable = CompositeDisposable()
    val state = MutableLiveData<DashboardState>()

    init {
        compositeDisposable.add(bindData())
    }

    private fun bindData(): Disposable =
        publishSubject
            .map { DashboardAction.LoadStatistic }
            .compose(retrieveStatistic.getChartInfo())
            .scan(DashboardState.idle(), reducer)
            .subscribe(::updateChart)

    private fun updateChart(dashState: DashboardState) {
        state.postValue(dashState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private val reducer = BiFunction { previousState: DashboardState, result: StatisticResult ->
            with(previousState){
                when (result) {
                    is StatisticResult.Success -> {
                        copy(
                            isLoading = false,
                            data = result.data
                        )
                    }
                    is StatisticResult.Failure -> {
                        copy(isLoading = false, error = result.error)
                    }
                    is StatisticResult.Loading -> {
                        copy(isLoading = true)
                    }
                }
            }
        }
    }
}