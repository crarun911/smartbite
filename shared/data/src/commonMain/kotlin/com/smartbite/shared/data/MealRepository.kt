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
                implementation(project(":shared:network"))

                implementation("io.insert-koin:koin-core:3.5.3")
            }
        }
    }
}

android {
    namespace = "com.smartbite.shared.data"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
}
