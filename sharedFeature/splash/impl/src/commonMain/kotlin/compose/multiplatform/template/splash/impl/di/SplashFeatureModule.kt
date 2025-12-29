package compose.multiplatform.template.splash.impl.di

import compose.multiplatform.template.splash.impl.screens.SplashViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val splashFeatureModule = module {
    // provide helper viewModels, repositories or any helper classes here
    factoryOf(::SplashViewModel)
}