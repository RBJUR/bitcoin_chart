package br.com.roquebuarque.bitcoinchart.application.di


import android.content.Context
import br.com.roquebuarque.bitcoinchart.application.BitcoinChartApplication
import br.com.roquebuarque.bitcoinchart.data.di.ServiceModule
import br.com.roquebuarque.bitcoinchart.presentation.di.ActivityModule
import br.com.roquebuarque.bitcoinchart.presentation.di.DashboardComponent
import br.com.roquebuarque.bitcoinchart.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ServiceModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: BitcoinChartApplication)

    fun createDashboardActivity(module: ActivityModule): DashboardComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}