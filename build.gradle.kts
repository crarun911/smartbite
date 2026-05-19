plugins {
    kotlin("multiplatform") version "2.0.20"apply false
    kotlin ("android") version "2.0.20"apply false
    id ("com.android.application") version "8.6.1"apply false
    id ("com.android.library") version "8.6.1"apply false
    id ("org.jetbrains.compose") version "1.6.11" apply false
}

repositories {
    google()
    mavenCentral()
}
