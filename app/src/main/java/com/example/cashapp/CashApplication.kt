package com.example.cashapp

import android.app.Application
import com.example.cashapp.data.DataModule
import com.example.cashapp.presentation.di.ViewModelModule
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class CashApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


        startKoin {
            modules(DataModule)
            modules(ViewModelModule)
        }
    }
}
