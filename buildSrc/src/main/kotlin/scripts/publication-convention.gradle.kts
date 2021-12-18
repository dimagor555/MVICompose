package scripts

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    `maven-publish`
    signing
}

ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

val localProperties = gradleLocalProperties(rootDir)
localProperties.onEach { (name, value) ->
    ext[name.toString()] = value
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhUsername")
                password = getExtraString("ossrhPassword")
            }
        }
    }
    publications.withType<MavenPublication> {
        artifact(javadocJar.get())
        pom {
            name.set("MVICompse")
            description.set("A simple MVI library for Kotlin, Compose and Android")
            url.set("https://github.com/dimagor555/MVICompose")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("dimagor555")
                    name.set("Dmitry Goryachev")
                    email.set("dimagor555.developer@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/dimagor555/MVICompose")
            }
        }
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

signing {
    sign(publishing.publications)
}