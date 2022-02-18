plugins { java }
sourceSets.main { java.srcDir("src") }
repositories(RepositoryHandler::mavenCentral)
dependencies { implementation(files("src/objectOriented/gridWorld/gridworld.jar")) }
