package compose.multiplatform.template

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import compose.multiplatform.template.splash.api.SplashRoute
import compose.multiplatform.template.splash.impl.screens.splashEntry

@Composable
@Preview
fun App() {
    MaterialTheme {
        // The app owns the back stack in Navigation 3.
        val backStack: SnapshotStateList<NavKey> = remember { mutableStateListOf(SplashRoute) }
        Scaffold {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    // Keeps remembered state and scopes a ViewModelStore per entry,
                    // so koinViewModel() in a screen is tied to its back-stack entry.
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
                entryProvider = entryProvider {
                    splashEntry()
                },
            )
        }
    }
}
