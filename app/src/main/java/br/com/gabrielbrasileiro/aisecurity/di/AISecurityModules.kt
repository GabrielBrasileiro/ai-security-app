package br.com.gabrielbrasileiro.aisecurity.di

import br.com.gabrielbrasileiro.aisecurity.menu.MenuViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AISecurityModules {

    fun load() = loadKoinModules(module {
        factoryViewModels()
    })

    private fun Module.factoryViewModels() {
        viewModel { MenuViewModel() }
    }
}