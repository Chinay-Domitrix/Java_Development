import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.10"
	java
	idea
}
allprojects {
	apply<JavaPlugin>()
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply<IdeaPlugin>()
	repositories(RepositoryHandler::mavenCentral)
	dependencies {
		implementation("org.jetbrains:annotations:23.0.0")
		implementation("junit:junit:4.13.2")
		implementation("org.json:json:20220320")
	}
	tasks.withType<JavaCompile> {
		sourceCompatibility = "17"
		targetCompatibility = "17"
	}
	val compileKotlin: KotlinCompile by tasks
	compileKotlin.kotlinOptions { jvmTarget = "17" }
	val compileTestKotlin: KotlinCompile by tasks
	compileTestKotlin.kotlinOptions { jvmTarget = "17" }
	tasks.test {
		useJUnit()
		maxHeapSize = "1G"
	}
}
repositories(RepositoryHandler::mavenCentral)
