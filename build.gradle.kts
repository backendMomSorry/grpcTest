import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.google.protobuf") version "0.8.18"
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.grpc:grpc-protobuf:1.51.0")
    implementation ("io.grpc:grpc-netty:1.51.0")
    implementation ("io.grpc:grpc-stub:1.51.0")
    implementation ("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation ("com.google.protobuf:protobuf-kotlin:3.21.9")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.9"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.51.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
//            if (it.name.startsWith("generateTestProto")) {
//                it.dependsOn("jar")
//            }

            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}



//
//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:3.7.1"
//    }
//    plugins {
//        // For android projects, uncomment the lines below
//        //javalite {
//        //    artifact = 'com.google.protobuf:protoc-gen-javalite:3.0.0'
//        //}
//
//        id("client") {
//            artifact = "com.github.googleapis:gapic-generator-kotlin:master-SNAPSHOT:core@jar"
//        }
//    }
//    generateProtoTasks {
//        all().forEach() { task ->
//            // For android projects, uncomment the lines below
//            //task.builtins {
//            //    remove java
//            //}
//            task.plugins {
//                // For android projects, uncomment the lines below
//                //javalite {}
//
//                // this generates your client library and helper Kotlin builders!
//                id("client") {}
//            }
//        }
//    }
//}