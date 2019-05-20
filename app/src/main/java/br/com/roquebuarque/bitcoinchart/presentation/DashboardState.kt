package br.com.roquebuarque.bitcoinchart.presentation

import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse

sealed class DashboardState{

    object IDLE: DashboardState()

    object LoadingState: DashboardState()

    object EndState: DashboardState()

    data class DataState(val data: BitcoinResponse): DashboardState()

    data class ErrorState(val error:Throwable): DashboardState()

}