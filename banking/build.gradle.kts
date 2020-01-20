val developmentOnly = configurations.create("developmentOnly")
configurations.runtimeClasspath.extendsFrom(developmentOnly)
configurations.compileOnly.extendsFrom(configurations.annotationProcessor.copy())

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.cloud:spring-cloud-starter-security")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    implementation("org.apache.logging.log4j:log4j:2.12.1")
    implementation("org.apache.logging.log4j:log4j-api:2.12.1")
}
