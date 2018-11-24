import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    mavenLocal()
    mavenCentral()
}

// Tweak to be sure to have compiler and dependency versions the same
extra["kotlin.version"] = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

plugins {
    val kotlinVersion = "1.3.10"

    application

    kotlin("jvm") version kotlinVersion
}

application {
    mainClassName = "io.github.CoroutineKt"
}

group = "io.github"
version = "0.0.1-SNAPSHOT"

tasks.withType<Jar> {
    baseName = "coroutine"
    version = "gradle"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    val coroutinesVersion = "1.0.1"

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
