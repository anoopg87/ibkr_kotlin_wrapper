plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "com.anooplab.ibwrapper"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(files("libs/ibapi.jar")) // IB API Java JAR dependency
}

kotlin {
    jvmToolchain(17)
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}
tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = "ibkr-kotlin-wrapper"
            version = project.version.toString()
            pom {
                name.set("IBKR Kotlin Wrapper")
                description.set("Idiomatic Kotlin wrapper for Interactive Brokers Java API (ibapi.jar)")
                url.set("https://github.com/anooplab/ibkr-kotlin-wrapper") // Update if needed
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("anooplab")
                        name.set("Anoop Gopalakrishnan")
                        email.set("anoop@anooplab.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/anooplab/ibkr-kotlin-wrapper.git")
                    developerConnection.set("scm:git:ssh://github.com:anooplab/ibkr-kotlin-wrapper.git")
                    url.set("https://github.com/anooplab/ibkr-kotlin-wrapper")
                }
            }
        }
    }
    repositories {
        maven {
            name = "localMaven"
            url = uri("${'$'}buildDir/repo")
        }
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/anooplab/ibkr-kotlin-wrapper")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String? ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as String? ?: ""
            }
        }
        // To publish to Maven Central, configure sonatype repository and credentials here.
        // Example (uncomment and fill in your details):
        // maven {
        //     name = "sonatype"
        //     url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
        //     credentials {
        //         username = project.findProperty("ossrhUsername") as String? ?: ""
        //         password = project.findProperty("ossrhPassword") as String? ?: ""
        //     }
        // }
    }
}
