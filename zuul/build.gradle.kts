val developmentOnly by configurations.creating
configurations {
	developmentOnly
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")
	implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
	implementation("org.springframework.cloud:spring-cloud-starter-security")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
