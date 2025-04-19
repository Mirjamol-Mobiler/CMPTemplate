import com.android.build.gradle.LibraryExtension
import compose.multiplatform.conventions.configureBaseAndroid
import compose.multiplatform.conventions.getPluginId
import compose.multiplatform.conventions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class BaseAndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.getPluginId("androidLibrary"))
                apply(libs.getPluginId("detekt"))
            }
            val androidExtension = extensions.getByType<LibraryExtension>()
            val packageName = libs.findVersion("packageName").get().toString()
            configureBaseAndroid(androidExtension)
            with(androidExtension) {
                namespace = packageName + getNameSpace(project.path)
            }
        }
    }

    private fun getNameSpace(path: String) = path.replace(":", ".")
}