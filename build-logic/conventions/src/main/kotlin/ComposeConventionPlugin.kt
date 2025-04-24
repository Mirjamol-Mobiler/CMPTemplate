import compose.multiplatform.conventions.configureCompose
import compose.multiplatform.conventions.getPluginId
import compose.multiplatform.conventions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply(libs.getPluginId("composeCompiler"))
                apply(libs.getPluginId("composeMultiplatform"))
                apply(libs.getPluginId("kotlinMultiplatform"))
            }
            val extension = extensions.getByType<KotlinMultiplatformExtension>()
            with(extensions.getByType<ComposeCompilerGradlePluginExtension>()) {
                includeSourceInformation.set(true)
            }
            configureCompose(extension)
        }
    }
}