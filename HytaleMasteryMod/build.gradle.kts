plugins {
    id("java")
}

group = "com.example"
version = "1.01-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))

}

tasks.test {
    useJUnitPlatform()
}