package br.com.roquebuarque.bitcoinchart.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.roquebuarque.bitcoinchart.presentation.DashboardViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DashboardViewModelModule {
    @Singleton
    @Provides
    internal fun provideViewModelProviderFactory(
        viewModel: DashboardViewModel
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(viewModel.javaClass)) {
                    return viewModel as T
                }
                throw IllegalArgumentException("unexpected viewModel class $modelClass")
            }
        }
    }
}