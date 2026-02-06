plugins {
    alias(libs.plugins.project.kmp)
    alias(libs.plugins.project.compose)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.components.resources)
            implementation(project.dependencies.platform(libs.koin.bom))
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