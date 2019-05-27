package br.com.roquebuarque.bitcoinchart.presentation

import android.util.Log
import io.reactivex.functions.Function

class StatisticMapper : Function<StatisticResult, DashboardState> {

    override fun apply(result: StatisticResult): DashboardState {
        return when (result) {
            is StatisticResult.Success -> {
                Log.d("StatisticResult", "Success")
                DashboardState(
                    isLoading = false,
                    data = result.data
                )
            }
            is StatisticResult.Failure -> {
                Log.d("StatisticResult", "Failure")
                DashboardState(isLoading = false, error = result.error)
            }
            is StatisticResult.Loading -> {
                Log.d("StatisticResult", "Loading")
                DashboardState(isLoading = true)
            }
        }
    }
}