plugins { kotlin("multiplatform") id("org.jetbrains.compose") id("com.android.library") }

kotlin { androidTarget()



    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation("io.insert-koin:koin-core:3.5.6")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            }
        }
        val androidMain by getting
        val iosMain by getting
    }
}

android { namespace = "com.smartbite.shared.core" compileSdk = 34 defaultConfig { minSdk = 24 } }