import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("com.preemptive.dasho.android")
    kotlin("android")
    kotlin("android.extensions")
}

dasho {
    enabledBuildVariants = "" // blank matches any variant (debug or release)
}

android{buildTypes{getByName("debug"){isMinifyEnabled =  true}}}
android{buildTypes{getByName("release"){isMinifyEnabled = true}}}

dependencies {
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
}

android {
    compileSdkVersion(29)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdkVersion(14)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
}

repositories {
    google()
    jcenter()
}
