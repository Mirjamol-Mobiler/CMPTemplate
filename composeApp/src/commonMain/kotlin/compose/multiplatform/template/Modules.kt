package compose.multiplatform.template

import cafe.adriel.voyager.core.registry.ScreenRegistry
import compose.multiplatform.template.splash.impl.di.splashFeatureModule
import compose.multiplatform.template.splash.impl.di.splashScreenModule

fun appModules() = listOf(
    splashFeatureModule
)

fun screenRegistry() = ScreenRegistry {
    splashScreenModule()
}