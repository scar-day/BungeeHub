import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.gradleup.shadow") version "8.3.1" apply false

    `java-library`
    java
}

group = "dev.scarday"
version = "2.0"

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.gradleup.shadow")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:24.1.0")

        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
    }

    tasks.withType<ShadowJar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("Hub-${project.name}.jar")
        destinationDirectory.set(file("${rootProject.projectDir}/compile"))
    }

    tasks.withType<ProcessResources> {
        include("**/*.yml")
        include("**/*.prop")
        include("**/*.zip")
        filter<org.apache.tools.ant.filters.ReplaceTokens>(
            "tokens" to mapOf(
                "ID" to rootProject.name.lowercase(),
                "NAME" to rootProject.name,
                "VERSION" to rootProject.version,
                "AUTHOR" to "ScarDay"
            )
        )
    }

    if (project.name == "velocity") {
        tasks.named<ProcessResources>("processResources") {
            from(sourceSets["main"].resources.srcDirs) {
                include("velocity-plugin.json")
            }
        }
    } // я не знаю почему, но при сборке velocity не складывается файл velocity-plugin.json
}