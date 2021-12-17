package scripts

import gradle.kotlin.dsl.accessors._89a50c7843ed4f07fb73edd87269a68f.publishing

plugins {
    `java-library`
    kotlin("jvm")
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    val kotlinSourcesJar by tasks
    publications {
        this.create<MavenPublication>("release") {
            from(components["java"])
            groupId = LibraryConfig.groupId
            artifactId = "${LibraryConfig.libraryName}-core"
            version = LibraryConfig.versionName
            artifact(kotlinSourcesJar)
        }
    }
}