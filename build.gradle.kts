@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
}

group = "dev.jimmymorales"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
        vendor.set(JvmVendorSpec.AZUL)
    }
}

detekt {
    config = rootProject.files("config/detekt/detekt.yml")
}

val detektJvmVersion = JavaVersion.VERSION_1_8.toString()
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = detektJvmVersion
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = detektJvmVersion
}

dependencies {
    detektPlugins(libs.detekt.formatting)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
