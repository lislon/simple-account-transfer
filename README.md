Simple Account Transfer
=======================

Demo project to demonstrate simple API without using `Spring` framework on `JDK8`.

API features: 
 - List/Adding of existing accounts
 - Currency transferring between them

The project was build with
 - Jersey (REST Framework)
 - hk2 (Dependency Injection)
 - junit/mockito (unit testing)
 - google thread weaver (unit testing - multi-threading)
 - jersey-test-framework-provider-inmemory (integration testing)
 - swagger (automatic documentation generation)

Building
========

```batch
gradle build
```

Running
=======
```batch
gradle application:run
```

The app will run on [http://localhost:8080/]

Trying it out
=============
Navigate to [http://localhost:8080/docs](documentation) and click `Try me out` buttons.