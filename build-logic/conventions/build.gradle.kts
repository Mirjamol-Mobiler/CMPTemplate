plugins {
    `kotlin-dsl`
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.kotlin.compose.compiler)
    compileOnly(libs.kotlin.serialization.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "project.compose"
            implementationClass = "ComposeConventionPlugin"
        }

        register("kmpTargets") {
            id = "project.kmp"
            implementationClass = "KmpConventionPlugin"
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