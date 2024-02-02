plugins {
    id("java")
    id ("org.springframework.boot") version ("2.6.2")
    id ("io.spring.dependency-management") version ("1.0.11.RELEASE")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}