package br.com.gabrielbrasileiro.aisecurity.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import br.com.gabrielbrasileiro.aisecurity.databinding.ActivityLoginBinding
import br.com.gabrielbrasileiro.aisecurity.menu.MenuActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        removeActionBar()
        setupActionListeners()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        loginButton.setOnClickListener {
            viewModel.sign(
                email = emailTextView.text?.toString(),
                password = passwordTextView.text?.toString()
            )
        }
    }

    private fun setupActionListeners() {
        lifecycleScope.launch {
            viewModel.signAction
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { action ->
                    when (action) {
                        LoginViewModel.LoginAction.OpenMenu -> showMenu()
                        LoginViewModel.LoginAction.ShowError -> showError()
                    }
                }
        }
    }

    private fun showMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun showError() {
        Toast.makeText(this, "The fields are not filled correctly", Toast.LENGTH_LONG).show()
    }

    private fun removeActionBar() {
        supportActionBar?.hide()
    }

}