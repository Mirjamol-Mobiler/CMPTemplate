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
            commonMain.dependencies {
                implementation(findLibrary("jetbrains.compose.material"))
                implementation(findLibrary("jetbrains.compose.material3"))
                implementation(findLibrary("jetbrains.compose.runtime"))
                implementation(findLibrary("jetbrains.compose.foundation"))
                implementation(findLibrary("jetbrains.compose.ui"))
                implementation(findLibrary("jetbrains.compose.ui.tooling.preview"))
                implementation(findLibrary("components.resources"))
            }
        }
        extensionCompose.configure<ResourcesExtension> {
            generateResClass = never
        }
    }
}