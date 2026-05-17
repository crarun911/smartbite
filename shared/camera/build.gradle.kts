plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting

        val androidMain by getting {
            dependencies {
                implementation("androidx.camera:camera-core:1.3.1")
                implementation("androidx.camera:camera-camera2:1.3.1")
                implementation("androidx.camera:camera-lifecycle:1.3.1")
            }
        }

        val iosMain by getting
    }
}

android {
    namespace = "com.smartbite.shared.camera"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
}
