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
    image = "odds-service"
    tags = setOf("latest")
  }
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
    ports = listOf("9090")
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

  implementation("org.springframework.grpc:spring-grpc-server-web-spring-boot-starter")
  //implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")
  implementation("io.grpc:grpc-kotlin-stub:1.4.3")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("io.grpc:grpc-services")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.springframework.grpc:spring-grpc-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test> { useJUnitPlatform() }
