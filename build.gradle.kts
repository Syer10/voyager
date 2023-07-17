buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev" )
    }

    dependencies {
        classpath(libs.plugin.serialization)
        classpath(libs.plugin.hilt)
        classpath(libs.plugin.ktlint)
        classpath(libs.plugin.maven)
        classpath(libs.plugin.multiplatform.compose)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    // Read in the signing.properties file if it is exists
    val signingPropsFile = rootProject.file("release/signing.properties")
    if (signingPropsFile.exists()) {
        java.util.Properties().apply {
            signingPropsFile.inputStream().use {
                load(it)
            }
        }.forEach { key1, value1 ->
            val key = key1.toString()
            val value = value1.toString()
            if (key == "signing.secretKeyRingFile") {
                // If this is the key ring, treat it as a relative path
                project.ext.set(key, rootProject.file(value).absolutePath)
            } else {
                project.ext.set(key, value)
            }
        }
    }
}
