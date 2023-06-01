import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("kapt") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    application
}

buildscript {
    repositories {
        mavenCentral()
    }
}

apply {
    plugin("kotlin")
}

group = "com.paranid5"
version = "V1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.1")
    implementation("org.jetbrains.exposed", "exposed-core", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.40.1")

    implementation("org.xerial:sqlite-jdbc:3.42.0.0")

    implementation("io.arrow-kt:arrow-optics:1.1.5")
    kapt("io.arrow-kt:arrow-meta:1.6.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "org.main.MainKt")
    }

    from(configurations.implementation.map { config -> config.map { if (it.isDirectory) it else zipTree(it) } })
}

application {
    mainClass.set("com.paranid5.presentation.main.MainKt")
}

val compileKotlin: KotlinCompile by tasks

kotlin {
    jvmToolchain(17)
}

compileKotlin.kotlinOptions {
    jvmTarget = "17"
    languageVersion = "1.8"
}