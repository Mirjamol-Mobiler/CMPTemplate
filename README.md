# CMPTemplate

Compose Multiplatform template targeting Android and iOS, built around a modular, feature-based setup with shared navigation, resources, and conventions.

## What you get
- Android + iOS targets with a shared Compose UI entry point.
- Feature modules split into `api` and `impl`.
- Shared `navigation` and `resources` modules.
- Gradle convention plugins under `build-logic`.
- Detekt configured for all subprojects.

## Tech stack
- Kotlin Multiplatform + Compose Multiplatform
- Voyager for navigation
- Koin for DI
- Ktor ready for networking
- KSP for code generation

## Project structure
- `composeApp` — Android app and iOS framework entry points.
- `sharedCommon/navigation` — shared navigation contracts and Voyager setup.
- `sharedCommon/resources` — Compose resources and shared assets.
- `sharedFeature/*` — feature modules with `api` and `impl` split.
- `build-logic` — Gradle convention plugins used by modules.
- `iosApp` — Xcode project and iOS entry point.

## Getting started
Prereqs:
- Android Studio + JDK 11+
- Xcode (for iOS)

Run Android:
```sh
./gradlew :composeApp:installDebug
```

Run iOS:
- Open `iosApp/iosApp.xcodeproj` in Xcode and run the `iosApp` target.

## Use this as a template
1. Rename the package:
   - Update `packageName` in `gradle/libs.versions.toml`.
   - Update `namespace` and `applicationId` in `composeApp/build.gradle.kts`.
   - Replace `compose.multiplatform.template` in source files and folders.
2. Rename the iOS app:
   - Update `BUNDLE_ID` and `APP_NAME` in `iosApp/Configuration/Config.xcconfig`.
3. Optional:
   - Update `rootProject.name` in `settings.gradle.kts`.

## Create a new feature module
This template provides two Gradle tasks:

Create a feature with `api` + `impl` modules (recommended):
```sh
./gradlew createFeatureModule -PfeatureName=profile -PbasePackage=com.example.app
```

Create a base feature module (uses `packageName` from `libs.versions.toml`):
```sh
./gradlew createBaseFeatureModule -PfeatureName=profile -PpackageName=com.example.app
```

Both tasks will:
- Create `sharedFeature/<feature>/api` and `sharedFeature/<feature>/impl`.
- Add the new modules to `settings.gradle.kts`.

## Where to start
- App entry: `composeApp/src/commonMain/kotlin/compose/multiplatform/template/App.kt`
- Example feature: `sharedFeature/splash/impl`
