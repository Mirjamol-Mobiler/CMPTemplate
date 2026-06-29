import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import compose.multiplatform.conventions.findLibrary
import compose.multiplatform.conventions.getPluginId
import compose.multiplatform.conventions.libs
import compose.multiplatform.conventions.sourceSets

class FeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.getPluginId("project.kmp"))
                // Type-safe navigation routes are declared as @Serializable in api modules.
                apply(libs.getPluginId("kotlin.serialization"))
            }
            val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
            with(kmpExtension) {
                sourceSets {
                    commonMain.dependencies {
                        api(findLibrary("kotlinx.serialization.core"))
                        // Navigation 3 routes are declared in api modules as NavKey.
                        api(findLibrary("navigation3.runtime"))
                    }
                }
            }
        }
    }
}