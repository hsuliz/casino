plugins {
  kotlin("jvm") version "1.9.25"
  id("com.google.protobuf") version "0.9.4"
}

repositories { mavenCentral() }

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
  implementation("io.grpc:grpc-stub:1.72.0")
  implementation("io.grpc:grpc-kotlin-stub:1.4.3")
  implementation("io.grpc:grpc-protobuf:1.72.0")
  implementation("com.google.protobuf:protobuf-kotlin:4.30.2")
}

kotlin { jvmToolchain { languageVersion = JavaLanguageVersion.of(21) } }

protobuf {
  protoc { artifact = "com.google.protobuf:protoc:4.30.2" }
  plugins {
    create("grpc") { artifact = "io.grpc:protoc-gen-grpc-java:1.72.0" }
    create("grpckt") { artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.3:jdk8@jar" }
  }
  generateProtoTasks {
    all().forEach {
      it.plugins {
        create("grpc")
        create("grpckt")
      }
      it.builtins { create("kotlin") }
    }
  }
}
