plugins {
    `kotlin-dsl`
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
    compileOnly(libs.kotlin.compose.compiler)
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "project.compose"
            implementationClass = "ComposeConventionPlugin"
        }

        register("detekt") {
            id = "project.detekt"
            implementationClass = "DetektConventionPlugin"
        }

        register("kmpTargets") {
            id = "project.kmp"
            implementationClass = "KmpConventionPlugin"
        }

        register("baseAndroid") {
            id = "project.base"
            implementationClass = "BaseAndroidLibraryConventionPlugin"
        }

        register("featureImpl") {
            id = "project.feature.impl"
            implementationClass = "FeatureImplConventionPlugin"
        }

        register("featureApi") {
            id = "project.feature.api"
            implementationClass = "FeatureApiConventionPlugin"
        }
    }
}