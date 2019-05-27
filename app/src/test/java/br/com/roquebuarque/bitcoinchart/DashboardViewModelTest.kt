package br.com.roquebuarque.bitcoinchart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    /* @Mock
     private lateinit var retrieveStatistic: RetrieveStatistic
     @Mock
     private lateinit var state: Observer<DashboardState>

     private lateinit var viewModel: DashboardViewModel


     private fun mockResponse(): BitcoinResponse {
       return BitcoinResponse(
             name= "Market Price (USD)",
             description = "Average USD market price across major bitcoin exchanges.",
             unit = "USD",
             period = "day",
             values = listOf(StatisticResponse(
                 timestamp = 1555718400,
                 value = 5302.9575F))
         )
     }*/
}