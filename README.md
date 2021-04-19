# Spring-QueryDSL-Template

## Goal

* Compose a Multi Module Structure project with **QueryDSL**

## Spec

* Java 11
* Spring Boot
* MySQL
* JPA & QueryDSL
* Gradle

## Structure

* Parent : module-common
* Child : module-api

> build.gradle in Root
``` java
project(':module-api') {
    dependencies {
        compile project(':module-common')
    }
} 
```

## Implement

* module-api : Controller, Service
* module-common : Repository, Property, Constatnt
