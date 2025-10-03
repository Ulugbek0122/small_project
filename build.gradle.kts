import org.gradle.internal.fingerprint.classpath.impl.ClasspathFingerprintingStrategy.compileClasspath
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.safeargs) apply false
//    alias(libs.plugins.dagger.hilt) apply false
//    id("dagger.hilt.android.plugin") version "2.46.1" apply false
//    id("kotlin-kapt") apply false
}

//buildscript {
//    dependencies {
//        classpath(libs.hilt.android.gradle.plugin)
//    }
//}
