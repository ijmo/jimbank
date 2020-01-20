import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

buildscript {
    dependencies {
        classpath("io.spring.gradle:dependency-management-plugin:1.0.8.RELEASE")
    }
}

plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
    id("org.springframework.boot") version "2.2.2.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("plugin.spring") version "1.3.61" apply false
    kotlin("plugin.jpa") version "1.3.61" apply false
}

allprojects {
    group = "ijmo.jimbank"
    version = "0.0.1-SNAPSHOT"

    repositories {
        jcenter()
    }
}

extra["springCloudVersion"] = "Hoxton.SR1"

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        kapt("org.springframework.boot:spring-boot-configuration-processor")
        compileOnly("org.springframework.boot:spring-boot-configuration-processor")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    configurations {
        all {
            exclude(group = "org.slf4j", module = "slf4j-jdk14")
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}
