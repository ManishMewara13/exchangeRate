plugins {
    id("java")
    id ("org.springframework.boot") version ("2.7.10")
    id ("io.spring.dependency-management") version ("1.0.11.RELEASE")
}

springBoot {
    mainClass.set("com.example.demo.ExchangeRateApplication")
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

//    implementation ("org.slf4j:slf4j-api:2.0.0-alpha1")
    implementation ("ch.qos.logback:logback-classic:1.2.6")
    implementation ("org.json","json", "20210307")
    implementation ("org.projectlombok:lombok:1.18.20")
}

tasks.test {
    useJUnitPlatform()
}