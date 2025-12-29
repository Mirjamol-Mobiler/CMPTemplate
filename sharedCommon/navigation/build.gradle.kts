plugins {
    alias(libs.plugins.project.base)
    alias(libs.plugins.project.kmp)
    alias(libs.plugins.project.compose)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.voyager.core)
                api(libs.voyager.navigator)
                api(libs.voyager.screenModel)
                api(libs.voyager.koin)
                api(libs.voyager.bottomSheetNavigator)
            }
        }
    }
}
