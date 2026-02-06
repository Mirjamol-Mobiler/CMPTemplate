package compose.multiplatform.conventions

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun Project.configureKMPTargets(extensions: KotlinMultiplatformExtension) =
    with(extensions) {
        compilerOptions {
            apiVersion.set(KotlinVersion.KOTLIN_2_3)
            freeCompilerArgs.addAll(
                listOf(
                    "-Xexpect-actual-classes",
                    "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-Xexplicit-backing-fields"
                )
            )
        }
        extensions.configure<KotlinMultiplatformAndroidLibraryExtension> {
            compileSdk = libs.getVersion("android-compileSdk").toInt()
            minSdk = libs.getVersion("android-minSdk").toInt()
            namespace = androidNamespace()
            experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
        }
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = this@configureKMPTargets.resolveFullName()
                isStatic = true
            }
        }
    }

private fun Project.androidNamespace(): String {
    val base = libs.getVersion("packageName")
    val modulePath = path.removePrefix(":").replace(":", ".")
    return "$base.$modulePath"
}
private fun Project.resolveFullName(): String {
    val nonRootParent = parent?.takeIf { it != rootProject } ?: return name
    return "${nonRootParent.resolveFullName()}_name"
}

internal fun KotlinMultiplatformExtension.sourceSets(
    configure: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit
) {
    (this as ExtensionAware).extensions.configure("sourceSets", configure)
}