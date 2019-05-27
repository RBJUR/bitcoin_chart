package br.com.roquebuarque.bitcoinchart.presentation

import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse

data class DashboardState(
    val isLoading: Boolean,
    val data: BitcoinResponse? = null,
    val error: Throwable? = null
) {
    companion object {
        fun idle(): DashboardState {
            return DashboardState(
                isLoading = false,
                data = null,
                error = null
            )
        }
    }
}




