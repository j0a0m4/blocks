import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
	kotlin("jvm") version "2.0.20"
	id("com.adarshr.test-logger") version "3.2.0"
	application
}

group = "io.j0a0m4"
version = "1.0"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
	testImplementation(kotlin("test"))
	testImplementation("io.kotest:kotest-assertions-core:5.6.2")
	testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
}

testlogger {
	theme = ThemeType.MOCHA_PARALLEL
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}

application {
	mainClass.set("MainKt")
}
