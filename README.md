# Spring-Multi-Module-QueryDSL-Template

## Goal

* Compose a Multi Module Structure project with **QueryDSL**

## Spec

* Java 11
* Spring Boot
* MySQL
* JPA & QueryDSL
* Gradle

## Structure

* Parent
  - module-common : Repository, Property, Constant

* Child
  - module-api : Controller, Service

> build.gradle in Root
``` java
project(':module-api') {
    dependencies {
        compile project(':module-common')
    }
} 
```

## Implement

* QueryDSL Setting Commit : [641b270](https://bit.ly/3x8LPxc)


## Reference

* [[gradle] 그레이들 Annotation processor 와 Querydsl](http://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html)
