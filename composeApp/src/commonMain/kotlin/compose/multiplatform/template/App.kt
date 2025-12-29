package compose.multiplatform.template

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.FadeTransition
import compose.multiplatform.template.splash.impl.screens.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalVoyagerApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(
            screen = SplashScreen,
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false),
        ) { navigator ->
            FadeTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}