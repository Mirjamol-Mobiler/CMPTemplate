package compose.multiplatform.conventions

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureBaseAndroid(extension: LibraryExtension) {
    with(extension) {
        compileSdk = libs.getVersionInt("android.compileSdk")
        defaultConfig {
            minSdk = libs.getVersionInt("android.minSdk")
        }

        sourceSets {
            getByName("main") {
                kotlin.directories.add("build/generated/moko/androidMain/src")
            }
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}