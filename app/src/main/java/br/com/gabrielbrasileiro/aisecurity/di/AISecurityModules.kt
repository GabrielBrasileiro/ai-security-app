package br.com.gabrielbrasileiro.aisecurity.di

import br.com.gabrielbrasileiro.aisecurity.domain.ValidateCredentialsUseCase
import br.com.gabrielbrasileiro.aisecurity.login.LoginViewModel
import br.com.gabrielbrasileiro.aisecurity.menu.MenuViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AISecurityModules {

    fun load() = loadKoinModules(module {
        factoryViewModels()
        factoryDomain()
    })

    private fun Module.factoryViewModels() {
        viewModel {
            LoginViewModel(validateCredentialsUseCase = get())
        }
        viewModel { MenuViewModel() }
    }

    private fun Module.factoryDomain() {
        factory { ValidateCredentialsUseCase() }
    }
}