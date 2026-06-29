package compose.multiplatform.template.splash.impl.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import compose.multiplatform.template.Res
import compose.multiplatform.template.compose_multiplatform
import compose.multiplatform.template.splash.api.SplashRoute
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

/**
 * Registers the splash destination on a Navigation 3 [EntryProviderScope].
 * Call this from the app's `entryProvider { }` block to add the splash screen.
 */
fun EntryProviderScope<NavKey>.splashEntry() {
    entry<SplashRoute> {
        SplashScreen()
    }
}

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
) {
    var showContent by remember { mutableStateOf(false) }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Hello from Splash")
                }
            }
        }
    }
}
