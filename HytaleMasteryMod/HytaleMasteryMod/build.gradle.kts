plugins {
    id("java")
}

group = "com.example"
version = "1.01-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy. EXCLUDE
    archiveBaseName.set("HytaleMasteryMod")
    archiveVersion.set("1.01")

    // IMPORTANT: This includes the UI files from resources in the JAR
    from("src/main/resources")
}