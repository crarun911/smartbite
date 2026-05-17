package com.smartbite.android

import android.app.Application
import com.smartbite.shared.core.coreModule
import com.smartbite.shared.network.networkModule
import com.smartbite.shared.data.dataModule
import com.smartbite.shared.domain.domainModule
import com.smartbite.shared.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SmartBiteApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SmartBiteApp)
            modules(
                coreModule,
                networkModule,
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}
