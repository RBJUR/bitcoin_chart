package br.com.roquebuarque.bitcoinchart.presentation.di

import br.com.roquebuarque.bitcoinchart.presentation.DashboardActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface DashboardComponent {

    fun inject(dashboardActivity: DashboardActivity)

}