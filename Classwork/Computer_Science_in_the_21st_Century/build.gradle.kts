plugins { java }
repositories(RepositoryHandler::mavenCentral)
sourceSets.all {
	java.srcDir("src")
	resources.srcDir("images")
}
