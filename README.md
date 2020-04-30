
[![Build Status](http://localhost:8090/buildStatus/icon?job=RestAssured%2Fdev)](http://localhost:8090/job/RestAssured/job/dev/)
   
   POJO classes to serialise and deserialize the request and response respectively.
   ````
   AddPlace.java
   Location.java
   DeletePlace.java
   ````
   Resources
   ````
   APIResources: Enum class to supply http method specific resource
   TestDataBuild: Build payload using POJO classes and consume data when called in definition.
   Utils: Utility for all the resuable methods like resquestSpec, responseSpec, getResource, readProperties, JSONPath
   global.properties: Contains project specific properties
   ````
   Step Definitions 
   ```
   BaseClass: 
   1. Based on Singleton design pattern 
   2. Contains getters and setters for the classes that are required for GIVEN, WHEN, THEN step definitions to work
   
   Hooks:
   1. Contains After, before and beforestep annotaions. 
   
   Givens, Whens, Thens:
   1. Classes contains step definitions as their name reflects.
   2. Create Baseclass object in it which gives access to APIResources, TestData, Utils.
   ```
   Reporting:
   ```
   1. Using Maven-cucumber-plugin to generate report
   2. logging.txt file will generate at run-time that will contain request payload and response along with hedears.
   ```