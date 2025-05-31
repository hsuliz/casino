plugins {
  kotlin("jvm") version "1.9.25"
  kotlin("plugin.spring") version "1.9.25"
  id("org.springframework.boot") version "3.5.0"
  id("io.spring.dependency-management") version "1.1.7"
  id("com.google.cloud.tools.jib") version "3.4.5"
}

group = "dev.hsuliz.casino"

version = "0.0.1-SNAPSHOT"

jib {
  from { image = "gcr.io/distroless/java21-debian12" }
  to {
    image = "gateway"
    tags = setOf("latest")
  }
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
    ports = listOf("8080")
  }
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

repositories { mavenCentral() }

extra["springCloudVersion"] = "2025.0.0"

dependencyManagement {
  imports {
    mavenBom(
        "org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

dependencies {
  implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux")
  implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-fabric8-all:3.2.1")

  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test> { useJUnitPlatform() }
