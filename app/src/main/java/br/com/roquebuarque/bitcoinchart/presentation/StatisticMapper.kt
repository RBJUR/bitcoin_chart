package br.com.roquebuarque.bitcoinchart.presentation
import io.reactivex.functions.Function

class StatisticMapper : Function<StatisticResult, DashboardState> {

    override fun apply(result: StatisticResult): DashboardState {
        return when (result) {
            is StatisticResult.Success -> {
                DashboardState(
                    isLoading = false,
                    data = result.data
                )
            }
            is StatisticResult.Failure -> {
                DashboardState(isLoading = false, error = result.error)
            }
            is StatisticResult.Loading -> {
                DashboardState(isLoading = true)
            }
        }
    }
}