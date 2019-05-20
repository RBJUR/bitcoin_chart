package br.com.roquebuarque.bitcoinchart.application

import android.app.Application
import br.com.roquebuarque.bitcoinchart.application.di.ApplicationComponent
import br.com.roquebuarque.bitcoinchart.application.di.DaggerApplicationComponent

class BitcoinChartApplication: Application() {

    private var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        getComponent().inject(this)

    }

    fun getComponent(): ApplicationComponent {
        if (component == null) {
           component = DaggerApplicationComponent.factory()
                .create(applicationContext)
        }
        return component as ApplicationComponent
    }
}