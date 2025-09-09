package br.com.gabrielbrasileiro.aisecurity.domain

class ValidateCredentialsUseCase {

    fun isValid(email: String, password: String): Boolean {
        val emailValid = isValidEmail(email)
        val passwordValid = isValidPassword(password)

        return emailValid && passwordValid
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }

}