package br.com.roquebuarque.bitcoinchart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse
import br.com.roquebuarque.bitcoinchart.data.StatisticResponse
import br.com.roquebuarque.bitcoinchart.domain.RetrieveStatistic
import br.com.roquebuarque.bitcoinchart.presentation.DashboardState
import br.com.roquebuarque.bitcoinchart.presentation.DashboardViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.Assert

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var retrieveStatistic: RetrieveStatistic
    @Mock
    private lateinit var state: Observer<DashboardState>

    private lateinit var viewModel: DashboardViewModel


    @Before
    fun setup(){
        //TO AVOID ExceptionInInitializerError
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {Schedulers.trampoline() }

        //TO AVOID NullpointerException in Subscribe
        Mockito.`when`(retrieveStatistic.getChartInfo()).thenReturn(
            Observable.just(DashboardState.IDLE))

        //INIT VIEW MODEL
        viewModel = DashboardViewModel(retrieveStatistic)

        //LIVEDATA MOCK
        viewModel.state.observeForever(state)
    }

    @Test
    fun dataStateTesting() {
        // given
        Mockito.`when`(retrieveStatistic.getChartInfo()).thenReturn(
            Observable.just(DashboardState.DataState(mockResponse())))

        // when
        viewModel.bindChartInfo()

        // then
        Mockito.verify(state).onChanged(viewModel.state.value)
        Assert.assertEquals(viewModel.state.value, DashboardState.DataState(mockResponse()))

    }

    @Test
    fun errorStateTesting() {
        // given
        val exception = UnknownError()
        Mockito.`when`(retrieveStatistic.getChartInfo()).thenReturn(
            Observable.just(DashboardState.ErrorState(exception)))

        // when
        viewModel.bindChartInfo()

        // then
        Mockito.verify(state).onChanged(viewModel.state.value)
        Assert.assertEquals(viewModel.state.value, DashboardState.ErrorState(exception))

    }

    @Test
    fun loadingStateTesting() {
        // given
        Mockito.`when`(retrieveStatistic.getChartInfo()).thenReturn(
            Observable.just(DashboardState.LoadingState))

        // when
        viewModel.bindChartInfo()

        // then
        Mockito.verify(state).onChanged(viewModel.state.value)
        Assert.assertEquals(viewModel.state.value, DashboardState.LoadingState)

    }

    @Test
    fun endStateTesting() {
        // given
        Mockito.`when`(retrieveStatistic.getChartInfo()).thenReturn(
            Observable.just(DashboardState.EndState))

        // when
        viewModel.bindChartInfo()

        // then
        Mockito.verify(state).onChanged(viewModel.state.value)
        Assert.assertEquals(viewModel.state.value, DashboardState.EndState)

    }

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
    }
}