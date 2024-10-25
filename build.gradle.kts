plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.school"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //Spring Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // Security
    // implementation("org.springframework.boot:spring-boot-starter-security")
    //Spring Boot Starter Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    //Devtools for automatic reloading
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    //Driver for MySQL
    runtimeOnly("com.mysql:mysql-connector-j")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //	testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //Lombok annotations
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Baeldung Guava
    implementation("com.google.guava:guava:33.3.1-jre")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
