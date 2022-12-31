dependencies { implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.1") }
repositories(RepositoryHandler::mavenCentral)
plugins { java }
sourceSets.all { java.srcDir("src") }
