package br.com.roquebuarque.bitcoinchart.presentation.di

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull

@Module
class ActivityModule(var activity: AppCompatActivity) {


    @Provides
    internal fun provideContext(): Context {
        return activity
    }

}