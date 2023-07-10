import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ProjectConfig.GRADLE)
        classpath(ProjectConfig.KOTLIN_GRADLE_PLUGIN)
    }
}
