package br.com.gabrielbrasileiro.aisecurity.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    private val _validationResult = MutableSharedFlow<Boolean>()
    val validationResult: SharedFlow<Boolean> = _validationResult.asSharedFlow()

    fun onNameChanged(newValue: String) {
        _name.value = newValue
    }

    fun onLastNameChanged(newValue: String) {
        _lastName.value = newValue
    }

    fun onAgeChanged(newValue: String) {
        _age.value = newValue
    }

    fun validate() {
        val isValidNow = name.value.isNotBlank() &&
                lastName.value.isNotBlank() &&
                age.value.isNotBlank()

        viewModelScope.launch {
            _validationResult.emit(isValidNow)
        }
    }

}