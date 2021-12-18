package scripts

import Android
import Deps

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("scripts.publication-convention")
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        version = Android.versionCode

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.composeVersion
    }
}

dependencies {
    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.lifecycleVmKtx)
    implementation(Deps.composeRuntime)
}

afterEvaluate {
    val releaseSourcesJar by tasks
    publishing {
        publications {
            this.create<MavenPublication>("release") {
                from(components["release"])
                groupId = LibraryConfig.groupId
                artifactId = "android"
                version = LibraryConfig.versionName
                artifact(releaseSourcesJar)
            }
        }
    }
}
