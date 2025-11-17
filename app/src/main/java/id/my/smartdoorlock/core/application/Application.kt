package id.my.smartdoorlock.core.application

import id.my.smartdoorlock.core.di.serviceModule
import id.my.smartdoorlock.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                serviceModule,
                viewModelModule,
            )
        }
    }
}