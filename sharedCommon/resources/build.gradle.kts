plugins {
    alias(libs.plugins.project.base)
    alias(libs.plugins.project.kmp)
    alias(libs.plugins.project.compose)
    id(libs.plugins.kotlin.serialization.get().pluginId) version libs.versions.kotlin
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(libs.koin.core)
        }
    }
}


compose.resources {
    publicResClass = true
    packageOfResClass = libs.versions.packageName.get()
    generateResClass = always
}

tasks.register("copyFrameworkResourcesToApp", Copy::class) {
    from("${layout.buildDirectory}/resources/main")
    into("${layout.buildDirectory}/xcode-frameworks/App")
}