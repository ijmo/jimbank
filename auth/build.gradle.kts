val developmentOnly by configurations.creating
configurations {
    developmentOnly
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencies {
//    compile(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.cloud:spring-cloud-starter-security")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.projectlombok:lombok")
}
