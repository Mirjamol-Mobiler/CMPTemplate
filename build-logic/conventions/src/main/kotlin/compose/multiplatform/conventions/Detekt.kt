package compose.multiplatform.conventions

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureDetekt(extension: DetektExtension) = extension.apply {
    tasks.withType<Detekt>().configureEach {
        reports {
            //reports are generated in the build/reports directory
            html.required.set(true) // required to generate the html report
            txt.required.set(true) // required to generate the txt report
        }
        exclude {
            it.file.absolutePath.contains("build\\")
        }
        dependencies {
            detektPlugins(findLibrary("detekt-formatting"))
        }
    }
}