package br.com.roquebuarque.bitcoinchart.presentation.di

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: AppCompatActivity) {


    @Provides
    internal fun provideContext(): Context {
        return activity
    }

}