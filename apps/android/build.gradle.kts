plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
    id("koin")
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
    implementation(project(":shared:core"))
    implementation(project(":shared:network"))
    implementation(project(":shared:data"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:ui"))
    implementation(project(":shared:camera"))

    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("io.insert-koin:koin-android:3.5.3")
    implementation(compose.ui)
    implementation(compose.material)
    implementation(compose.foundation)
}
