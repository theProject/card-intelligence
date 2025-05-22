// build.gradle.kts (Project level: Your Root Project Folder)

// The buildscript block for navigation-safe-args is an older pattern.
// While it works, for newer projects, plugin management is often centralized
// in settings.gradle.kts or directly in module-level plugins blocks with versions from catalogs.
// For now, ensure this version aligns with your navigation component versions.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Safe args plugin for navigation
        // Ensure this version is compatible with your other navigation libraries (e.g., 2.7.7)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}

plugins {
    // Declare the versions of plugins to be used by modules.
    // These versions should ideally match what you have in your libs.versions.toml for consistency.
    // For example, if libs.versions.toml has agp = "8.10.0" and kotlinPlugin = "1.9.22"

    id("com.android.application") version "8.10.0" apply false
    id("com.android.library") version "8.10.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    // If you plan to use KSP across multiple modules, you might declare its plugin here too:
    // id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false // Ensure version matches your ksp version in libs.toml
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
