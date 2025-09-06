package br.com.gabrielbrasileiro.aisecurity.menu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

internal class MenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MenuScreen() }
    }

}

@Composable
fun MenuScreen(viewModel: MenuViewModel = koinViewModel()) {
    val context = LocalContext.current

    val name by viewModel.name.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val age by viewModel.age.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.validationResult.collect { isValid ->
            val message = if (isValid) {
                "All fields are filled!"
            } else {
                "Please fill out all the fields."
            }

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { viewModel.onNameChanged(it) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = lastName,
                onValueChange = { viewModel.onLastNameChanged(it) },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = age,
                onValueChange = { viewModel.onAgeChanged(it) },
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.validate() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send")
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}
