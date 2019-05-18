package br.com.roquebuarque.bitcoinchart.application.di

import android.content.Context
import br.com.roquebuarque.bitcoinchart.application.BitcoinChartApplication
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: BitcoinChartApplication): Context {
        return app.applicationContext
    }

}