plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ibkr-kotlin-wrapper"))
    implementation(files("../ibkr-kotlin-wrapper/libs/ibapi.jar"))
}

application {
    mainClass.set("com.anooplab.ibwrapper.example.AccountsApiExampleKt")
}
