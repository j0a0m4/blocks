import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
	kotlin("jvm") version "1.8.20"
	id("com.adarshr.test-logger") version "3.2.0"
	application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
	testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
	testImplementation("io.kotest:kotest-assertions-core:5.6.2")
	testImplementation("io.kotest:kotest-property:5.6.2")
}

testlogger {
	theme = ThemeType.MOCHA_PARALLEL
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(11)
}

application {
	mainClass.set("MainKt")
}
