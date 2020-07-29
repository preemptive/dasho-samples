
buildscript {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        maven("https://maven.preemptive.com/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("com.preemptive.dasho:dasho-android:1.3.+")
        classpath(kotlin("gradle-plugin", "1.3.72"))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
