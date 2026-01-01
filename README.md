# Spring Boot Mongodb Example

This example application combines Spring Boot in its current version `4.0.1` with MongoDB. It provides a REST API for managing albums and songs.

Using the `spring-boot-docker-compose` library, a MongoDB database is started locally for development - see `docker-compose.yml`. In addition, the REST API can be accessed via Swagger UI under `http://localhost:8080/swagger-ui.html`.

The entities are set up as `@Document` with repositories using `spring-boot-starter-data-mongodb`. For relationships, `@DocumentReference` is used - while MongoDB internally stores only the target id, Spring Boot automatically resolves the reference when needed. The album entity also contains a `MetaData` subfield.

**Create your own Spring Boot application and define custom documents and relationships** at [Bootify.io](https://bootify.io). Numerous configuration options and modern best practices are included.

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services. [Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own `application-local.properties` file to override settings for development.

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
gradlew clean build
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./build/libs/mongodb-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as environment variable when running the container.

```
gradlew bootBuildImage --imageName=io.bootify/mongodb
```

## Further readings

* [Gradle user manual](https://docs.gradle.org/)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data MongoDB reference](https://docs.spring.io/spring-data/mongodb/reference/)
