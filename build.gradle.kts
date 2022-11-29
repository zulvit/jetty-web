plugins {
    id("java")
}

group = "ru.zulvit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("org.jetbrains:annotations:20.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-server
    implementation("org.eclipse.jetty:jetty-server:11.0.12")
    // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-servlet
    implementation("org.eclipse.jetty:jetty-servlet:11.0.12")
    // https://mvnrepository.com/artifact/org.mortbay.jetty/jetty
    implementation("org.mortbay.jetty:jetty:7.0.0.pre5")
    // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-security
    implementation("org.eclipse.jetty:jetty-security:11.0.12")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.5")

    implementation("org.flywaydb:flyway-core:9.8.2")
    implementation("org.postgresql:postgresql:42.5.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}