import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinxKover)
    alias(libs.plugins.serialization)
    alias(libs.plugins.dokka)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }

    sourceSets.all {
        compilerOptions {
            freeCompilerArgs.add("-opt-in=kotlin.experimental.ExperimentalTypeInference")
            freeCompilerArgs.add("-opt-in=kotlin.contracts.ExperimentalContracts")
            freeCompilerArgs.add("-Xsuppress-warning=NOTHING_TO_INLINE")
        }
    }
}

android {
    namespace = "io.github.mhss1.kubit"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

group = "io.github.mhss1"
version = "1.0.0-alpha02"

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "kubit", version.toString())

    pom {
        name = "Kubit"
        description = "Micro-optimized, fast, memory-efficient Kotlin utilities."
        inceptionYear = "2025"
        url = "https://github.com/mhss1/kubit"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "mhss1"
                name = "Mohamed Shaaban"
                url = "https://github.com/mhss1"
            }
        }
        scm {
            url = "https://github.com/mhss1/kubit"
            connection = "scm:git:git://github.com/mhss1/kubit.git"
            developerConnection = "scm:git:ssh://git@github.com/mhss1/kubit.git"
        }
    }
}

dokka {
    dokkaPublications.html {
        moduleName.set("Kubit")
        outputDirectory.set(file("$rootDir/docs"))
    }
    pluginsConfiguration.html {
        customStyleSheets.from("$rootDir/assets/logo-styles.css")
        customAssets.from("$rootDir/assets/logo.png")
    }
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(projectDir.resolve("src"))
            remoteUrl.set(URI("https://github.com/mhss1/kubit/blob/main/kubit/src"))
            remoteLineSuffix.set("#L")
        }
    }
}