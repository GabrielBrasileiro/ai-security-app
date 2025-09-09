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

    fun sign(email: String?, password: String?) {
        val isValid = validateCredentialsUseCase.isValid(
            email = email.orEmpty().trim(),
            password = password.orEmpty().trim()
        )
        val action = if (isValid) LoginAction.OpenMenu else LoginAction.ShowError

        viewModelScope.launch { _signAction.emit(action) }
    }

    sealed class LoginAction {
        data object OpenMenu : LoginAction()
        data object ShowError : LoginAction()
    }

}