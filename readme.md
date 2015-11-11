PAMM - SEED
-----------

PAMM-SEED is a template for building Web Applications using the PAMM stack:

-   [Play](https://www.playframework.com/) for server side web pages and services

-   [AngularJS](https://angularjs.org/) for client side rich user interfaces

-   [MariaDb](https://mariadb.org/) for relational data

-   [MongoDb](https://www.mongodb.org/community) for document based non-relational data

Index
-----

**[1. Architecture](#Architecture)**

**[2. Play Component](#PlayComponent)**

- [Http Layer](#HttpLayer)
- [Business Service Layer](#BusinessServiceLayer)
- [Repository Layer](#RepositoryLayer)
- [Persistence Layer](#PersistenceLayer)

**[3. Angular Component](#AngularComponent)**

**[4. Testing](#Testing)**

- [Application End to End Testing](#ApplicationEndToEndTesting)
- [Angular Client Unit Testing](#AngularClientTesting)
- [Play Unit Testing](#PlayUnitTesting)

**[5. Running The Application](#RunningTheApplication)**

<a name="Architecture"></a>
1. Architecture
---------------

The following diagram shows the high level reference architecture for the application: ![](./docs/img/pamm.gif)

<a name="PlayComponent"></a>
2. Play Component
-----------------

The Play component of the PAMM seed consists of the following layers:

![](./docs/img/play.gif)

<a name="HttpLayer"></a>
### 2.1 Http Layer ###

The Http layer exposes the applications RESTful API to clients, facilitated by the [Play framework routing mechanism](https://www.playframework.com/documentation/2.4.3/JavaRouting). Each resource endpoint exposes a RESTful API for a single application resource.

The Resource Endpoints responsibility is to accept requests for a resource and delegate the processing of that request to a Business Service Layer component. The Transactional boundary for the processing of a request is defined on the Action methods of the Resource Endpoint. All resource endpoint implementations should extend the [ResourceEndpoint](./svc/app/controllers/resource/play/ResourceEndpoint.java) superclass. See [ProjectEndpoint](./svc/app/controllers/resource/play/ProjectsEndpoint.java) for a basic implementation.

<a name="BusinessServiceLayer"></a>
### 2.2 Business Service Layer ###

The Business Service layer services provide a [façade](https://en.wikipedia.org/wiki/Facade_pattern) style interface to a number of underlying service operations. Each service is an aggregation of service operations, with each service operation providing business logic to maintain application resources.

Each service operation should inherit from the ServiceOperation superclass, with any application "cross cutting" behaviour (e.g. application level authentication and authorization, audit, error handling) being managed by the ServiceOperation superclass. Comments have been included in this class as a placeholder for this logic to be included if required.

<a name="RepositoryLayer"></a>
### 2.3 Repository Layer ###

The Repository is an abstraction layer, hiding details of any persistence mechanism from the applications business logic in the Business Service layer. This abstraction layer enables the persistence mechanism to be changed without having an effect on the Business layer. It also facilitates testing of the Business layer in isolation from the Persistence layer.

This seed provides a basic example of a Repository implementation, the [ProjectJpaRepository](./svc/app/models/repository/jpa/ProjectJpaRepository.java), which persists to a Relational H2 database, using the JPA interface with a Hibernate implementation. 

To change the persistence mechanism, a new implementation of the [Repository`<Project`>](./svc/app/models/services/project/ProjectRepository.java) needs to be created and injected into the Business layers service operations. To change the implementation of the Repository being injected into the Business Layer operations, amend the bindings defined in [RepositoriesModule](./svc/app/util/inject/play/RepositoriesModule.java).

A Repository should be created for the "aggregate root" (i.e. the root object in the object graph) of an application feature or component. Defining the granularity of repositories is something that has to be carried out on a project by project basis. 

E.g. If we have a feature called Manage Person Details, where a Person has an associated address, employment, income etc, then the aggregate root would be the object from which all these details can be accessed, which in this example would most likely be Person. A Repository`<Person>` interface would then be defined and used by the Business Layer to access Person and its associated data. A Repository`<Person>` implementation would be created for the required persistence mechanism. 

<a name="PersistenceLayer"></a>
### 2.4 Persistence Layer ###

The Persistence layer provides the application persistence mechanism and its persistence model. This seed uses JPA with a Hibernate implementation to persist data to an in-memory H2 database. 

#### 2.4.1 Configuration ####

This requires the following configuration (already set up in the seed).

a) conf/application.conf - Defines configuration for the Datasource to access the H2 database. Specifies the jdbc url, the jndi name for the Datasource, the H2 database driver.

The default persistence unit for the application is also defined in the application.conf file by setting the jpa.default property. When play.db.jpa.JPA.em() is invoked with no arguments, it will return an Entity manager for this default persistence unit. 

b) conf/META-INF/persistence.xml - Defines the persistence unit for the application, specifying Hibernate as the persistence provider and referencing the H2 database datasource.

c) build.sbt - include the dependencies for javaJpa and hibernate.

#### 2.4.2 High Level Class Structure ####
![](./docs/img/persistence.gif)

*GenericReadOnlyDao*: Base class for all application Data access objects, providing methods to find, list and search for entities. An EntityManager instance is made available through the EntityManagerProvider factory class. This is obtained from the play.db.jpa.JPA.em() static method returning an entity manager instance for the currently running thread.

*GenericDao*: Provides generic methods to create, update and delete entities.

*EntityManagerProvider*: Factory class to encapsulate the play.db.jpa.JPA static method to return the EntityManager instance for the currently running thread.

Any entity specific queries should be placed in the Dao associated with that entity. E.g. If the ProjectEntity requires a database query not included in the Generic Dao classes, then a method for that database query should be added to the ProjectDao class.

<a name="AngularComponent"></a>
3. Angular Component
--------------------

*In Development*

<a name="Testing"></a>
4. Testing
----------

<a name="ApplicationEndToEndTesting"></a>
### 4.1 Application End To End Testing ###

For the Application End To End Testing, we are using the Protractor end to end testing framework along with the Cucumber BDD tool. 

Protractor (and our end to end test scripts) run on a node.js server and communicate with a Selenium server through Selenium Webdriver API bindings. This link provides an overview of the [Protractor testing components](https://angular.github.io/protractor/#/infrastructure) and how they collaborate to run the end to end tests:


#### 4.1.1 Prerequisites ####

Modules required to run the Protractor end to end tests are specified in the [Protractor readMe file](svc/test/cucumber/README_protractor)

#### 4.1.2 Test Configuration ####

The following files have been configured to run the Protractor end to end tests for the seed:

**[Protractor conf.js file](svc/test/cucumber/conf.js)**

The following link provides a comprehensive list of properties that can be set for your [Protractor configuration](https://github.com/angular/protractor/blob/master/docs/referenceConf.js), and descriptions of how to use these properties.


**[package.json](./package.json)**

The following link provides a comprehensive list of properties that can be set for your [npm package.json configuration](https://docs.npmjs.com/files/package.json), and descriptions of how to use these properties.


#### 4.1.3 Test structure ####

Each feature being tested will have the following fileset located under the following base folder

		svc/test/cucumber/features/


- A Gherkin feature file defining the Feature to be tested and the test scenarios for the feature. See the [manage-project.feature](svc/test/cucumber/features/manage-project/manage-project.feature) for an example feature file.


- A cucumber script implementing the steps defined in the Gherkin feature file, to test the scenarios in the Gherkin feature file. See the [manage-project step definition](svc/test/cucumber/features/manage-project/stepDefinitions.js) for an example cucumber step definition script.
 

- Page Object script(s), encapsulating the user interface actions required by the cucumber scripts in order to implement the scenarios defined in the Gherkin feature file.  See the [manage-project page model](svc/test/cucumber/features/page-models/manage-project.page.js) for an example page model.


Scenarios that require data setup, should make use of the testsetup child project included in the seed. This is a Play application exposing a RESTful API for the execution of SQL scripts. To invoke a SQL script through the testsetup application, cucumber scripts should invoke the executeScript(scriptNumber) function of the following script. 

		svc/test/cucumber/features/util/setup-service-caller.js

See the call to setup.executeScript(1) in the  [manage-project step definition](svc/test/cucumber/features/manage-project/stepDefinitions.js) for an example.  

The SQL scripts to set up test data should be placed in the following directory of the testsetup child project

		testsetup/conf/sql-scripts/ 

and referenced in the [config.properties](./testsetup/conf/sql-scripts/config.properties) file.


#### 4.1.4 Test Execution ####

**Running from command line**

To run the Protractor tests open a command window at the PAMM seed root folder.

Enter the following command

	npm run protractor-test

This will invoke the protractor-test "event" in the [package.json](./package.json) file, which runs the command "./node_modules/.bin/protractor svc/test/cucumber/conf.js"

This will output the Gherkin style scenario description for each scenario run, and display which tests have passed or failed. 

**Running as part of project build**

The [build.sbt](build.sbt) file has been configured to invoke the Protractor tests to execute  as part of the sbt endToEndTest task.

The [E2ETestTask.scala](project/E2ETestTask.scala) file contains the definition for this task. The task will start up a selenium server, start an H2 database, run the svc project, run the testsetup project and finally run the protractor tests.

To run the Protractor tests through the build task, open a command window at the PAMM seed root folder and run the following command

    activator svc/endToEndTest

This will invoke all processes on which the Protractor tests are dependent (Selenium Server, H2 database server, svc Play application and testsetup Play application). Once these processes have successfully started, the Protractor tests will be run. After completion of the Protractor tests, all dependent processes are automatically stopped by this build task.

The output from the Test run is written to the following folder under the PAMM seed root folder

	test-e2e-results/CCYY-MM-DD-HH.mm.ss.SSS

where CCYY-MM-DD-HH.mm.ss.SSS is the timestamp for the test run. Each test run will result in a new folder being created to store its test output. The Protractor test results are stored in the file protractorResults.log in the above folder.


<a name="AngularClientUnitTesting"></a>
### 4.2 Angular Client Unit Testing ###

For the unit testing of the Angular client components, Jasmine test framework libraries are used to create the test functions, with these tests being run by the karma test runner framework.

#### 4.2.1 Prerequisites ####

Modules that are required to run the jasmine unit tests with the karma test runner framework are specified in the [Karma readMe file](svc/test/unit/README_karma).


#### 4.2.2 Test Configuration ####

The following files have been configured to invoke the karma test runner to execute the jasmine unit tests:

**[karma.conf.js:](svc/test/unit/karma.conf.js)**

The following link provides a comprehensive list of properties that can be set for your [Karma configuration](http://karma-runner.github.io/0.13/config/configuration-file.html), and descriptions of how to use these properties.


**[package.json](./package.json)**

The following link provides a comprehensive list of properties that can be set for your [npm package.json configuration](https://docs.npmjs.com/files/package.json), and descriptions of how to use these properties.


#### 4.2.3 Test structure ####

For unit testing of our Angular javascript components, the following convention has been followed.

For each javascript component that we unit test within the folder

		svc/public/webapp

a corresponding jasmine unit test component has been created in an identically named folder hierarchy within the folder

		svc/test/unit 

e.g. in order to test the component

		svc/public/webapp/feature/login/login-controller.js 

we would create the following jasmine test script 

		svc/test/unit/feature/login/login-controller.spec.js 

and html file

		svc/test/unit/feature/login/login-controller.test.html 


Each test has an html file defining the dependencies required to run the individual tests and a javascript file containing the jasmine unit test scripts to be executed.

See [login-controller.test.html](svc/test/unit/feature/login/login-controller.test.html) for an example of an html file defining the test dependencies, and [login-controller.spec.js](svc/test/unit/feature/login/login-controller.spec.js) for example jasmine test scripts.


#### 4.2.4 Test Execution ####


**Running from command line**

To run the jasmine tests with the karma test runner, open a command window at the PAMM seed root folder.

Enter the following command

     npm run jasmine-test

This will invoke the test "event" in the [package.json](./package.json) file, which runs the command "./node_modules/.bin/karma start svc/test/unit/karma.conf.js"

Any unsuccessful tests will be displayed on the command window, with details of the test that failed and the name of the jasmine file. 

**Running as part of project build**

The [build.sbt](build.sbt) file has been configured to invoke the karma test runner to execute the jasmine unit tests as part of the sbt test task.

The [ClientTestTask.scala](project/ClientTestTask.scala) file contains the definition for this client test task

To run the karma tests open a command window at the project root and enter the following command

    activator svc/test

This will invoke the Play unit tests as well as the Angular client unit tests.


<a name="PlayUnitTesting"></a>
### 4.3 Play Unit Testing ###

The PAMM seed folder structure adheres to the Play application convention, so in order for unit tests in the Play application to be invoked as part of the "sbt test" task, simply follow the instructions as detailed on the [Play Framework Testing page](https://www.playframework.com/documentation/2.4.3/JavaTest). 


<a name="RunningTheApplication"></a>
5. Running the Application
----------

Open a command window at the PAMM seed root folder and enter the following command

	buildscripts\batch\startDatabase.bat

This will start the H2 database server on port 9092.

The start the application by running the following command from the PAMM seed root folder

    activator svc/run

Once the "Server started" message is displayed in the command window, access the following URL in your browser

    http://localhost:9000

The PAMM login page should be presented. The seed has no authentication configured for this initial draft version, so by entering any username and password, the PAMM dashboard should be displayed. The angular client is integrated with the Play backend and any actions on the Angular client will be routed to the Play backend.




