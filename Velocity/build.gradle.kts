plugins {
    kotlin("jvm") version "2.1.0-Beta1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.7.2"
}

group = "me.moonboygamer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.spongepowered.org/repository/maven-public/") {
        name = "sponge-repo"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    paperweight.paperDevBundle("1.21-R0.1-SNAPSHOT")
    implementation("org.spongepowered:mixin:0.8.5") // Sponge Mixin library
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks {
    shadowJar {
        // Add Mixin configuration file to the JAR
        archiveClassifier.set("") // Ensures it's just the main jar
        from("src/main/resources") {
            include("mixins.velocity.json") // Include your Mixin config file
            include("mixins.velocity.refmap.json") // Include the refmap file if applicable
        }

        // Ensure Mixin configuration is written to JAR manifest
        manifest {
            attributes(
                "Main-Class" to "org.bukkit.plugin.java.JavaPlugin", // Make sure to adjust to the main class if needed
                "MixinConfigs" to "mixins.velocity.json" // Add Mixin configuration here
            )
        }
    }

    build {
        dependsOn(shadowJar) // Ensure build task generates shadow JAR
    }
}
