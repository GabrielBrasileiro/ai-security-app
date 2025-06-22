package br.com.gabrielbrasileiro.aisecurity.application

import android.app.Application
import br.com.gabrielbrasileiro.aisecurity.di.AISecurityModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        loadKoinModules()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@MainApplication)
        }
    }

    private fun loadKoinModules() {
        AISecurityModules().load()
    }
}