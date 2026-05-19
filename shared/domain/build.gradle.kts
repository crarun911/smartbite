plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
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

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            }
        }

        val androidMain by getting

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

android {
    namespace = "com.smartbite.shared.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
}