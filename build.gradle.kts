import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val group = "dev.f2a"
val pluginVersion = "0.2.0"
val mcVersion = "1.21.4"
java.sourceCompatibility=JavaVersion.VERSION_21

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.skriptlang.org/releases")

}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.google.http-client:google-http-client:1.43.3")
    implementation("com.google.http-client:google-http-client-apache-v2:1.43.3")
    compileOnly("com.github.SkriptLang:Skript:2.11.0")
    compileOnly("io.papermc.paper:paper-api:${mcVersion}-R0.1-SNAPSHOT")
}

bukkit {
    main = "dev.f2a.addon.skriptwebapi.SkriptWebAPI"

    apiVersion = "1.21"

    name = "Skweapi"
    authors = listOf("ft")
    description = "Web request and HTTP Server addon for Skript"
    version = pluginVersion
    depend = listOf("Skript")
    prefix = "Skweapi"

    /*
    permissions {
        register("per.*") {
            children = listOf("per.m")
        }
        register("per.m") {
            description = "Permission description"
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
     */
}

tasks {
    "shadowJar"(ShadowJar::class) {
        archiveBaseName.set("SkriptWebAPI")
        archiveClassifier.set("")
        archiveVersion.set(pluginVersion)
    }
}