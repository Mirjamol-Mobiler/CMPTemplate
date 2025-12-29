package compose.multiplatform.template.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface SharedScreens : ScreenProvider {
    /**
     * Shared screens references here to manage navigation between different feature modules` screens
     **/
}