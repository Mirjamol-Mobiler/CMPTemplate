# CMPTemplate

Compose Multiplatform template targeting Android and iOS, built around a modular, feature-based setup with shared resources and Gradle conventions.

## What you get
- Android + iOS targets with a shared Compose UI entry point.
- Feature modules split into `api` and `impl`.
- Official Navigation 3 with type-safe `NavKey` routes.
- Shared `resources` module.
- Gradle convention plugins under `build-logic`.
- Edge-to-edge Android UI with a transparent status bar.
- Detekt configured for all subprojects.

## Tech stack
- Kotlin Multiplatform + Compose Multiplatform
- Navigation 3 (official Compose Multiplatform navigation)
- Koin for DI
- Ktor ready for networking
- KSP for code generation

## Project structure
- `androidApp` — Android application module (sets up edge-to-edge / transparent status bar).
- `composeApp` — shared Compose UI library and iOS framework entry points.
- `sharedCommon/resources` — Compose resources and shared assets.
- `sharedFeature/*` — feature modules with `api` and `impl` split.
- `build-logic` — Gradle convention plugins used by modules.
- `iosApp` — Xcode project and iOS entry point.

## Navigation
Uses official Compose Multiplatform **Navigation 3**:
- Each destination is a `@Serializable` `NavKey`, declared in the feature's `api` module (e.g. `SplashRoute`).
- Each feature's `impl` module contributes its screen through an `EntryProviderScope<NavKey>` extension (e.g. `splashEntry()`).
- `composeApp` owns the back stack and the `NavDisplay` in `App.kt`.

There is no shared navigation module: feature-local routes live in each feature's `api`. Add a dedicated module for shared `NavKey`s only once multiple features need to navigate to each other.

## Getting started
Prereqs:
- Android Studio + JDK 11+
- Xcode (for iOS)

Run Android:
```sh
./gradlew :androidApp:installDebug
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
</content>
