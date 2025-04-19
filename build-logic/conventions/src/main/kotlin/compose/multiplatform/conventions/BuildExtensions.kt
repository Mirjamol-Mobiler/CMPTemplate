package compose.multiplatform.conventions

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.findLibrary(libraryName: String): Provider<MinimalExternalModuleDependency> {
    return libs.findLibrary(libraryName).get()
}

internal fun VersionCatalog.getPluginId(id: String) = findPlugin(id).get().get().pluginId

internal fun VersionCatalog.getVersion(alias: String) = this.findVersion(alias).get().requiredVersion

internal fun VersionCatalog.getVersionInt(alias: String) = this.findVersion(alias).get().requiredVersion.toInt()

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? {
    return add("implementation", dependencyNotation)
}

internal fun DependencyHandlerScope.api(dependencyNotation: Any): Dependency? {
    return add("api", dependencyNotation)
}

internal fun DependencyHandlerScope.detektPlugins(dependencyNotation: Any): Dependency? {
    return add("detektPlugins", dependencyNotation)
}