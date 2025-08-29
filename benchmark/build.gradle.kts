import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    id("application")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.allOpen)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kapt)
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
dependencies {
    implementation(project(":kubit"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.openjdk.jol.core)
    implementation(libs.jmh.core)
    kapt(libs.jmh.generator.annprocess)

    implementation(libs.kandy)

}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}
application {
    mainClass.set("benchmark.MainKt")
}

tasks.register<JavaExec>("main") {
    group = "benchmark"
    mainClass.set("org.openjdk.jmh.Main")
    classpath = sourceSets["main"].runtimeClasspath
    val resultsFile = layout.buildDirectory.file("reports/benchmarks/${System.currentTimeMillis()}/results.json")
    args = listOf(
        "benchmark.benchmarks.*", // package filter
        "-wi", "10",              // warmup iterations
        "-i", "15",               // measurement iterations
        "-r", "1s",               // runtime per measurement iteration
        "-w", "500ms",            // warmup time per iteration
        "-bm", "avgt",            // mode
        "-tu", "ns",              // time unit
        "-f", "2",                // number of measurement forks
        "-foe", "true",           // fail on error
        "-prof", "gc",            // Memory profiler (GC)
        "-rf", "json",            // Output format: JSON
        "-rff", resultsFile.get().asFile.absolutePath // Output file path
    )
    doFirst { resultsFile.get().asFile.parentFile.mkdirs() }
    finalizedBy("processBenchmarkResults")
}
tasks.register<JavaExec>("smoke") {
    group = "benchmark"
    mainClass.set("org.openjdk.jmh.Main")
    classpath = sourceSets["main"].runtimeClasspath
    val resultsFile = layout.buildDirectory.file("reports/benchmarks/${System.currentTimeMillis()}/results.json")
    args = listOf(
        "benchmark.benchmarks.*", // package filter
        "-wi", "2",               // warmup iterations
        "-i", "3",                // measurement iterations
        "-r", "600ms",            // runtime per measurement iteration
        "-w", "600ms",            // warmup time per iteration
        "-bm", "avgt",            // mode
        "-tu", "ns",              // time unit
        "-f", "1",                // number of measurement forks
        "-foe", "true",           // fail on error
        "-prof", "gc",            // Memory profiler (GC)
        "-rf", "json",            // Output format: JSON
        "-rff", resultsFile.get().asFile.absolutePath // Output file path
    )
    doFirst { resultsFile.get().asFile.parentFile.mkdirs() }
    finalizedBy("processBenchmarkResults")
}

tasks.register<JavaExec>("processBenchmarkResults") {
    group = "benchmark"
    description = "Runs MainKt after benchmarks"
    mainClass.set(application.mainClass)
    classpath = sourceSets["main"].runtimeClasspath
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}