package compose.multiplatform.template.splash.impl.di

import compose.multiplatform.template.splash.impl.screens.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashFeatureModule = module {
    // provide viewModels, repositories or any helper classes here
    viewModelOf(::SplashViewModel)
}