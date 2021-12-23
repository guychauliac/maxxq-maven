# MavenDependencyResolver

Resolve maven dependencies programmatically through simple to use Java classes.

## For single pom

    Set<Dependency> resolvedDependencies = new ResolveDependencies("https://repo1.maven.org/maven2/")
                                           .getDependencies(InputStream pomStream)

## For reactor build

   
	 Set<Dependency> resolvedDependencies = new ResolveDependencies("https://repo1.maven.org/maven2/")
                                           .getDependencies(InputStream pomStream1, InputStream pomStream2, ...)
## Advanced config for repositories

	ResolveDependencies resolveDependencies = new ResolveDependencies(
            new VirtualRepository()
                    .addRepository(new InMemoryCachingRepository(new FileCachingRepository(Paths.get("c:/data/pomcache/"), new RemoteRepository(RemoteRepository.MAVEN_CENTRAL))))
                    .addRepository(new LocalInMemoryRepository())); //at least 1 repo is required to which writing is possible for storage of the reactor pom's
                    
    resolveDependencies.getDependencies(InputStream pomStream)
    
## Using custom request builder

A custom request builder can be given to RemoteRepository to craft request with the specific authentication that might be required for private repositories

	new RemoteRepository(RemoteRepository.MAVEN_CENTRAL, customRequestBuilder)


## resolver logic

- traverse parent pom's: copy properties, dependencies and dependency management dependencies if not already existing
- resolve properties in dependency management 
- recursively follow pom includes in dependency management
- apply dependency management on existing dependencies 
- obtain transitive dependencies recursively. Do not replace existing dependencies (shortest path rule) 
- reapply dependency management on existing dependencies 
