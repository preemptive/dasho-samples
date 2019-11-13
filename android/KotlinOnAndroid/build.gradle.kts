
buildscript {
    val kotlin_version = "1.3.50"
    repositories {
        maven("https://maven.preemptive.com/")
        google()
        jcenter()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.2")
        classpath("com.preemptive.dasho:dasho-android:1.0.+")
        classpath(kotlin("gradle-plugin", "1.3.50"))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
