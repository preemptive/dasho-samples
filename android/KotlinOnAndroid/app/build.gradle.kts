import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("com.preemptive.dasho.android")
    kotlin("android")
    kotlin("android.extensions")
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.0")
}

android {
    compileSdkVersion(30)

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "coroutine-rule.txt")
            isMinifyEnabled = true
        }
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "coroutine-rule.txt")
            isMinifyEnabled = true
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
}

dasho {
    enabledBuildVariants = "" // blank matches any variant (debug or release)
}
