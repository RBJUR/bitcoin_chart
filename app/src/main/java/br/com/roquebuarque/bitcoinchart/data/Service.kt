package br.com.roquebuarque.bitcoinchart.data

import io.reactivex.Observable
import retrofit2.http.GET

interface Service {

    @GET("charts/market-price?timespan=4weeks&format=json")
    fun fetchBitcoinStatistic(): Observable<BitcoinResponse>

}