import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        val kotlinVersion = "1.4.30"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

val arrowVersion by extra { "0.13.1" }

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("kapt") version "1.4.32"
    application
}

apply {
    plugin("kotlin")
}

group = "me.arseny"
version = "V1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.xerial:sqlite-jdbc:3.34.0")
    implementation("io.arrow-kt:arrow-optics:$arrowVersion")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "org.main.MainKt")
    }

    from(configurations.compile.map { config -> config.map { if (it.isDirectory) it else zipTree(it) } })
}

application {
    mainClassName = "org.main.MainKt"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}