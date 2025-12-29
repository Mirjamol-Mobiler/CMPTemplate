package compose.multiplatform.template

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    screenRegistry()
    App()
}