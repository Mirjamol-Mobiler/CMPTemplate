plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    id(libs.plugins.detekt.get().pluginId) version libs.versions.detekt
    id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.ksp)
    }
}

detekt {
    toolVersion = libs.versions.detekt.get()
    buildUponDefaultConfig = true
    config.setFrom("$projectDir/config/detekt/detekt.yml")
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

tasks.register("createBaseFeatureModule") {
    group = "feature"
    description = "This helps to create base new feature module with api and impl"

    fun createBaseFeatureModule(
        dir: File,
        buildContent: String,
        packageName: String,
        moduleName: String
    ) {
        dir.apply {
            mkdirs()
            file("$this/build.gradle.kts").writeText(buildContent)
            file("$this/src/commonMain/kotlin/$packageName/$moduleName").mkdirs()
        }
    }

    doLast {
        val featureName = project.properties["featureName"]?.toString()
            ?: GradleException("Please specify feature name using PfeatureName=featureName")
        val packageName = project.properties["packageName"]?.toString() ?: libs.versions.packageName
        val basePath = "sharedFeature/$featureName"

        val apiBuildContent = "plugins {\n    alias(libs.plugins.project.feature.api)\n}"
        val implBuildContent = """
            plugins {
                alias(libs.plugins.project.feature.impl)
            }

            kotlin {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(projects.sharedFeature.$featureName.api)
                        }
                    }
                }
            }
        """.trimIndent()
        createBaseFeatureModule(
            file("$basePath/api"),
            apiBuildContent,
            packageName.toString(),
            "$featureName/impl"
        )
        createBaseFeatureModule(
            file("$basePath/impl"),
            implBuildContent,
            packageName.toString(),
            "$featureName/impl"
        )

        val settingsFile = file("settings.gradle.kts")
        val includeLines = """
            include("sharedFeature:$featureName:api")
            include("sharedFeature:$featureName:impl")
        """.trimIndent()
        if (!settingsFile.readText().contains(":$featureName.api")) {
            settingsFile.appendText("\n$includeLines")
        }
        println("$featureName module created successfully at $packageName")
    }
}

tasks.register("createFeatureModule") {
    group = "feature"
    description = "Create feature module with api and impl"

    fun createModule(
        dir: File,
        buildContent: String,
        packagePath: String,
        moduleType: String
    ) {
        dir.mkdirs()
        dir.resolve("build.gradle.kts").writeText(buildContent)
        dir.resolve("src/commonMain/kotlin/$packagePath/$moduleType").mkdirs()
    }

    doLast {
        val featureName = project.findProperty("featureName")?.toString() ?: error("Use -PfeatureName=featureName")
        val basePackage = project.findProperty("basePackage")?.toString() ?: libs.versions.packageName.get()

        val basePath = "sharedFeature/$featureName"
        val packagePath = basePackage.replace(".", "/") + "/$featureName"

        val apiBuildContent = """
            plugins {
                alias(libs.plugins.project.feature.api)
            }
        """.trimIndent()

        val implBuildContent = """
            plugins {
                alias(libs.plugins.project.feature.impl)
            }

            kotlin {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(projects.sharedFeature.$featureName.api)
                        }
                    }
                }
            }
        """.trimIndent()

        createModule(file("$basePath/api"), apiBuildContent, packagePath, "api")
        createModule(file("$basePath/impl"), implBuildContent, packagePath, "impl")

        val settingsFile = file("settings.gradle.kts")
        val includeBlock = """
            
            include(":sharedFeature:$featureName:api")
            include(":sharedFeature:$featureName:impl")
        """.trimIndent()

        if (!settingsFile.readText()
                .contains(":sharedFeature:$featureName:api")
        ) {
            settingsFile.appendText(includeBlock)
        }

        println("Feature '$featureName' created in $basePath")
    }
}
