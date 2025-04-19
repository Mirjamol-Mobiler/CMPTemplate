package compose.multiplatform.conventions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureCompose(extension: KotlinMultiplatformExtension) {
    val extensionCompose = extensions.getByType<ComposeExtension>()
    val composeDependencies = extensionCompose.dependencies

    with(extension) {
        sourceSets {
            androidMain.dependencies {
                implementation(findLibrary("androidx.activity.compose"))
            }
            commonMain.dependencies {
                implementation(composeDependencies.material)
                implementation(composeDependencies.material3)
                implementation(composeDependencies.runtime)
                implementation(composeDependencies.foundation)
                implementation(composeDependencies.ui)
                implementation(composeDependencies.components.resources)
            }
        }
        extensionCompose.configure<ResourcesExtension> {
            generateResClass = never
        }
    }
}