plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    with(libs.plugins) {
        alias(androidApplication) apply false
        alias(androidLibrary) apply false
        alias(composeCompiler) apply false
        alias(composeMultiplatform) apply false
        alias(kotlinMultiplatform) apply false
        alias(jetbrainsKotlinJvm) apply false
        id(detekt.get().pluginId) version libs.versions.detekt
        id(ksp.get().pluginId) version libs.versions.ksp
    }
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
    description = "This helps to create new feature module with api and impl"

    fun createModule(dir: File, buildContent: String, packagePath: String, moduleType: String) {
        dir.apply {
            mkdirs()
            file("$this/build.gradle.kts").writeText(buildContent)
            file("$this/src/main/commonMain/kotlin/$packagePath/$moduleType").mkdirs()
        }
    }

    doLast {
        val featureName = project.properties["featureName"]?.toString()
            ?: GradleException("Please specify feature name using PfeatureName=featureName")
        val moduleName = project.properties["moduleName"]?.toString()
            ?: GradleException("Please specify feature name using PfeatureName=featureName")
        val basePackage = project.properties["basePackage"]?.toString() ?: libs.versions.packageName

        val basePath = "sharedFeature/$moduleName/$featureName"
        val packagePath = "$basePackage/$moduleName/$featureName"
        val apiBuildContent = "plugins {\n    alias(libs.plugins.project.feature.api)\n}"
        val implBuildContent = """
            plugins {
                alias(libs.plugins.project.feature.impl)
            }

            kotlin {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(projects.sharedFeature.$moduleName.$featureName.api)
                            implementation(libs.voyager.tabNavigator)
                        }
                    }
                }
            }
        """.trimIndent()
        createModule(file("$basePath/api"), apiBuildContent, packagePath, "api")
        createModule(file("$basePath/impl"), implBuildContent, packagePath, "impl")

        val settingsFile = file("settings.gradle.kts")
        val includeLines = """
            include(":sharedFeature:$moduleName:$featureName:api")
            include(":sharedFeature:$moduleName:$featureName:impl")
        """.trimIndent()
        if (!settingsFile.readText().contains(":sharedFeature:$moduleName:$featureName.api")) {
            settingsFile.appendText("\n$includeLines")
        }
        println("$featureName module created successfully at $basePath")
    }
}