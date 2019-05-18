package br.com.roquebuarque.bitcoinchart.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import br.com.roquebuarque.bitcoinchart.R
import br.com.roquebuarque.bitcoinchart.application.BitcoinChartApplication
import br.com.roquebuarque.bitcoinchart.presentation.di.ActivityModule
import br.com.roquebuarque.bitcoinchart.presentation.di.DashboardComponent
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject
import br.com.roquebuarque.bitcoinchart.data.BitcoinResponse
import br.com.roquebuarque.bitcoinchart.domain.BitcoinDataMapper
import br.com.roquebuarque.bitcoinchart.util.MaskUtil
import com.google.android.material.snackbar.Snackbar


class DashboardActivity : BaseActivityInjecting<DashboardComponent>() {

    @Inject
    lateinit var viewModel: DashboardViewModel

    @Inject
    lateinit var mapper: BitcoinDataMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        viewModel.state.observe(this, Observer {
            render(it)
        })

    }

    private fun render(state: DashboardState) {
        when (state) {
            is DashboardState.DataState -> renderDataState(state.data)
            is DashboardState.LoadingState -> renderLoadingState()
            is DashboardState.EndState -> renderEndState()
            is DashboardState.ErrorState -> renderErrorState(state.error)
        }
    }

    private fun renderDataState(data: BitcoinResponse) {
        cardViewDashboard.visibility = View.VISIBLE
        val chartInfo = mapper.apply(data)
        txtTitleDashboard.text = chartInfo.name
        txtDescDashboard.text = chartInfo.description
        txtUnitDashboard.text = getString(R.string.unit, chartInfo.unit)
        txtPeriodDashboard.text = getString(R.string.period, chartInfo.period)
        val maskValue = MaskUtil.formatMoney(
            chartInfo.pointDate[chartInfo.pointDate.size -1].value.toDouble(),
            true,
            2)
        txtCurrentValueDashboard.text =  maskValue

        lineChart.apply {
            updateValues(chartInfo.pointDate)
            labelText(getString(R.string.chart_label))
            execute()
        }
    }

    private fun renderErrorState(error: Throwable) {
        val snackbar = Snackbar
            .make(mainCtnDashboard, "Error!", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun renderEndState() {
        loading.visibility = View.GONE
    }

    private fun renderLoadingState() {
        loading.visibility = View.VISIBLE
    }

    override fun onInject(component: DashboardComponent) {
        component.inject(this)
    }

    override fun createComponent(): DashboardComponent {
        val app = BitcoinChartApplication::class.java.cast(application)
        val activityModule = ActivityModule(this)
        return app?.getComponent()!!.createDashboardActivity(activityModule)
    }

}