plugins {
    alias(libs.plugins.project.feature.impl)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.sharedFeature.splash.api)
            }
        }
    }
}