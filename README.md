# Spring-Multi-Module-QueryDSL-Template

## Goal

* Compose a Multi Module Structure project with **QueryDSL**

## Spec

* Java 11
* Spring Boot 2.4.4
* MySQL
* JPA & QueryDSL
* Gradle 6.8.3

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

## Setup

* Need to add **DB config** in module-common to /config/custom/db.yml

![image](https://user-images.githubusercontent.com/18522341/115961986-16020880-a554-11eb-8c98-76f727b24c8c.png)




## Implement

* QueryDSL Setting Commit : [641b270](https://bit.ly/3x8LPxc)


## Reference

* [[gradle] 그레이들 Annotation processor 와 Querydsl](http://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html)
