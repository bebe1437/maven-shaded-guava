# Maven-Shaded-Guava
shaded guava for test

# Maven Install Shaded Jar
* shaded module: mvn clean package
* install shaded module: 

		mvn install -Dfile=/../target/module-guava-1.0-SNAPSHOT.jar \
		-DgroupId=org.example \
		-DartifactId=module-guava \
		-Dversion=1.0-SNAPSHOT \
		-Dpackaging=jar    
