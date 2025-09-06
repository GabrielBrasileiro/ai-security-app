package br.com.gabrielbrasileiro.aisecurity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabrielbrasileiro.aisecurity.domain.ValidateCredentialsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val validateCredentialsUseCase: ValidateCredentialsUseCase
) : ViewModel() {

    private val _signAction = MutableSharedFlow<LoginAction>()
    val signAction: SharedFlow<LoginAction> = _signAction.asSharedFlow()

    fun sign(email: String?, password: String?) = viewModelScope.launch {
        val isValid = validateCredentialsUseCase.isValid(
            email = email.orEmpty().trim(),
            password = password.orEmpty().trim()
        )

        if (isValid) {
            _signAction.emit(LoginAction.OpenMenu)
        } else {
            _signAction.emit(LoginAction.ShowError)
        }
    }

    sealed class LoginAction {
        data object OpenMenu : LoginAction()
        data object ShowError : LoginAction()
    }

}