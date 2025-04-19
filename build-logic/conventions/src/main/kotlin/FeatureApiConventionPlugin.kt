import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import compose.multiplatform.conventions.getPluginId
import compose.multiplatform.conventions.libs
import compose.multiplatform.conventions.sourceSets

class FeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.getPluginId("project.base"))
                apply(libs.getPluginId("project.kmp"))
            }
            val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
            with(kmpExtension) {
                sourceSets {
                    commonMain.dependencies {
                        //Dependencies that need to use in Feature Api module
                    }
                }
            }
        }
    }
}