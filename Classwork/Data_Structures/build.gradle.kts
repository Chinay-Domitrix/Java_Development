dependencies { implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0") }
repositories(RepositoryHandler::mavenCentral)
plugins { java }
repositories {
	mavenCentral()
}
sourceSets.all { java.srcDir("src") }
