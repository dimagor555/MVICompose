package scripts

import LibraryConfig

plugins {
    `java-library`
    kotlin("jvm")
    id("scripts.publication-convention")
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
            artifactId = "core"
            version = LibraryConfig.versionName
            artifact(kotlinSourcesJar)
        }
    }
}