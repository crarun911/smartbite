plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
}

android {
    namespace = "com.smartbite.shared.camera"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
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

        // FIX: create iosMain explicitly (IMPORTANT)
        val iosMain by creating {
            dependsOn(commonMain)
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}