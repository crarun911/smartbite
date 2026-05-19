plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.smartbite.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smartbite.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures { compose = true }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
}

dependencies {
    // Compose
    implementation(compose.ui)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

// Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

// Koin (no plugin needed)
    implementation("io.insert-koin:koin-android:3.5.6")
    implementation("io.insert-koin:koin-core:3.5.6")
// optional for Compose helpers
    implementation("io.insert-koin:koin-androidx-compose:3.5.6")

// CameraX
    implementation("androidx.camera:camera-core:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")

}
