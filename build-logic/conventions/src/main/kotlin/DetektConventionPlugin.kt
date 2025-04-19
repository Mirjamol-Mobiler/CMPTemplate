import compose.multiplatform.conventions.configureDetekt
import compose.multiplatform.conventions.getPluginId
import compose.multiplatform.conventions.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.getPluginId("detekt"))
            val extension = extensions.getByType<DetektExtension>()
            configureDetekt(extension)
        }
    }
}