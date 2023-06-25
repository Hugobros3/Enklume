import org.gradle.kotlin.dsl.`maven-publish`

/*
 * Enklume
 * Java library for parsing Minecraft save files 
 */

plugins {
	java
	`java-library`
	`maven-publish`
}

tasks.register<Javadoc>("cancer") {
	isFailOnError = false
	source = sourceSets.main.get().allJava
}

version = "1.1.0"

group = "xyz.chunkstories"
description = "Java library for parsing Minecraft save files"

repositories {
	mavenLocal()
	mavenCentral()

	maven("https://jitpack.io")
}

buildscript {
	repositories {
		mavenCentral()
	}
}

val compileJava : JavaCompile by tasks
compileJava.apply {
	// options.compilerArgs.add("-Xlint:none")
	options.encoding = "utf-8"
}

compileJava.options.debugOptions.debugLevel = "source,lines,vars"

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["java"])
		}
	}
}

dependencies {
	implementation("com.googlecode.json-simple", name = "json-simple", version = "1.1.1")
}