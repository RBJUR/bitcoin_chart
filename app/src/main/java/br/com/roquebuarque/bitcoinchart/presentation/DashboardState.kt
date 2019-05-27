package br.com.roquebuarque.bitcoinchart.presentation

import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse

data class DashboardState(
    val isLoading: Boolean,
    val data: BitcoinResponse? = null,
    val error: Throwable? = null
)




