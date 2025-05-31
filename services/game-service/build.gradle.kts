plugins {
  kotlin("jvm") version "1.9.25"
  kotlin("plugin.spring") version "1.9.25"
  id("org.springframework.boot") version "3.4.5"
  id("io.spring.dependency-management") version "1.1.7"
  id("com.google.cloud.tools.jib") version "3.4.5"
}

group = "dev.hsuliz.casino"
version = "0.0.1-SNAPSHOT"

jib {
  from { image = "gcr.io/distroless/java21-debian12" }
  to {
    image = "game-service"
    tags = setOf("latest")
  }
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
    ports = listOf("8080")
  }
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

repositories { mavenCentral() }

extra["springGrpcVersion"] = "0.8.0"

dependencyManagement {
  imports {
    mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
  }
}

dependencies {
  implementation(project(":proto-api"))

  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")
  implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-fabric8-all:3.2.1")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("io.micrometer:micrometer-registry-prometheus")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  implementation("io.grpc:grpc-kotlin-stub:1.4.3")
  implementation("io.grpc:grpc-services")
  implementation("com.google.protobuf:protobuf-kotlin:4.30.2")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.grpc:spring-grpc-test")
  testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.0")
  testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.0")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test>().configureEach { useJUnitPlatform() }
