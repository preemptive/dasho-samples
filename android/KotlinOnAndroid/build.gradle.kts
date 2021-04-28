
buildscript {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        maven("https://maven.preemptive.com/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("com.preemptive.dasho:dasho-android:1.4.+")
        classpath(kotlin("gradle-plugin", "1.4.10"))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
