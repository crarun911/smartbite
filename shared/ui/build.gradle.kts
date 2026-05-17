plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core"))
                implementation(project(":shared:domain"))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation("io.insert-koin:koin-core:3.5.3")
            }
        }
    }
}

android {
    namespace = "com.smartbite.shared.ui"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
}
