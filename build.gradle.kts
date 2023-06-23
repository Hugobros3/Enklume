/*
 * Enklume
 * Java library for parsing Minecraft save files 
 */

plugins {
	java
	`java-library`
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

/*configurations {
	deployerJars
}*/

dependencies {
	implementation("com.googlecode.json-simple", name = "json-simple", version = "1.1.1")
}