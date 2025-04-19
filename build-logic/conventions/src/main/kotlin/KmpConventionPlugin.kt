import compose.multiplatform.conventions.configureKMPTargets
import compose.multiplatform.conventions.getPluginId
import compose.multiplatform.conventions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.getPluginId("kotlinMultiplatform"))
            }
            val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
            configureKMPTargets(kmpExtension)
        }
    }
}