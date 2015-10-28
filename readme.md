PAMM - SEED
-----------

PAMM-SEED is a template for building Web Applications using the PAMM stack:

-   [Play](https://www.playframework.com/) for server side web pages and services

-   [AngularJS](https://angularjs.org/) for client side rich user interfaces

-   [MariaDb](https://mariadb.org/) for relational data

-   [MongoDb](https://www.mongodb.org/community) for document based non-relational data

Index
-----

**1. Architecture**

**2. Play Component**

    1.  Http Layer

    2.  Business Service Layer

    3.  Data Persistence Layer

**3. Angular Component**

**4. Testing**

    1.  Application End to End Testing

    2.  Angular Client Unit Testing

    3.  Play Unit Testing

**5. Running the application**

1. Architecture
---------------

The following diagram shows the high level reference architecture for the application: ![](./docs/img/pamm.gif)

2. Play Component
-----------------

The Play component of the PAMM seed consists of the following component layers:

### 2.1 Http Layer ###

The Http layer exposes the applications RESTful API to clients, facilitated by the [Play framework routing mechanism](https://www.playframework.com/documentation/2.4.3/JavaRouting). Each resource endpoint exposes a RESTful API for a single application resource.

The Resource Endpoints responsibility is to accept requests for a resource and delegate the processing of that request to a Business Service Layer component. The Transactional boundary for the processing of a request is defined on the Action methods of the Resource Endpoints.![](./docs/img/play.gif)

### 2.2 Business Service Layer ###

The Business Service layer services provide a [façade](https://en.wikipedia.org/wiki/Facade_pattern) style interface to a number of underlying service operations. Each service is an aggregation of service operations, with each service operation providing business logic to maintain application resources.

Each service operation should inherit from the ServiceOperation superclass, with any application "cross cutting" behaviour (e.g. application level authentication and authorization, audit, error handling) being managed by the ServiceOperation superclass. Comments have been included in this class as a placeholder for this logic to be included if required.

### 2.3 Data Persistence Layer ###

The Data Persistence layer provides the application persistence mechanism and its data model. This seed uses JPA with a Hibernate implementation to persist data to an in-memory H2 database. 

#### 2.3.1 Configuration ####

This requires the following configuration (already set up in the seed).

a) conf/application.conf - Defines configuration for the Datasource to access the H2 database. Specifies the jdbc url, the jndi name for the Datasource, the H2 database driver.

The default persistence unit for the application is also defined in the application.conf file by setting the jpa.default property. When play.db.jpa.JPA.em() is invoked with no arguments, it will return an Entity manager for this default persistence unit. 

b) conf/META-INF/persistence.xml - Defines the persistence unit for the application, specifying Hibernate as the persistence provider and referencing the H2 database datasource.

c) build.sbt - include the dependencies for javaJpa and hibernate.

#### 2.3.2 High Level Class Structure ####
![](./docs/img/persistence.gif)

*GenericReadOnlyDao*: Base class for all application Data access objects, providing methods to find, list and search for entities. An EntityManager instance is made available through the EntityManagerProvider factory class. This is obtained from the play.db.jpa.JPA.em() static method returning an entity manager instance for the currently running thread.

*GenericDao*: Provides generic methods to create, update and delete entities.

*EntityManagerProvider*: Factory class to encapsulate the play.db.jpa.JPA static method to return the EntityManager instance for the currently running thread.

Any entity specific queries should be placed in the Dao associated with that entity. E.g. If the ProjectEntity requires a database query not included in the Generic Dao classes, then a method for that database query should be added to the ProjectDao class.


3. Angular Component
--------------------

*In Development*

4. Testing
----------


### 4.1 Application End To End Testing ###

*In Development*


### 4.2 Angular Client Unit Testing ###

For the unit testing of the Angular client components, Jasmine test framework libraries are used to create the test functions, with these tests being run by the karma test runner framework.

#### 4.2.1 Pre requisites ####

Modules that are required for jasmine unit tests to be run with the karma test runner framework are specified in the [Karma readMe file](svc/test/unit/README_karma).



#### 4.2.2 Test Configuration ####

The following configuration files are required to invoke the karma test runner to execute the jasmine unit tests:

**[karma.conf.js:](svc/test/unit/karma.conf.js)**

The karma.conf.js file defines the following configuration properties:

*frameworks*: set to jasmine for unit testing of the seed. Note that the frameworks in Karma require a plugin / framework library to be installed via npm. For the seed, the jasmine library dependency is defined in the [package.json](svc/test/unit/package.json) file.


*basePath*: root path used to resolve all relative paths defined in the [karma.conf.js:](svc/test/unit/karma.conf.js) file.

*preprocessors*: preprocessors allow you to do some work to your files before they get served to the browser. Preprocessors generally have to be loaded as plugins (e.g. coverage, ng-html2js). For the seed, the coverage plugin is configured to be run, and is installed as the karma-coverage plugin defined as a dependency in the [package.json](svc/test/unit/package.json) file.

*files*: the list of files / file patterns that you want to load in the browser.

*exclude*: the list of files file patterns that you want to exclude from being loaded in the browser.

*port*: the webserver port 

*colors*: Enable or diable colours in the output (reports / logs)

*logLevel*: specify the level of logging to be output

*browsers*: list of browsers to launch and capture.

*reporters*: list of reporters to use for the tests. For the seed, progress and coverage reporters are specified. The progress reporter is bundled with karma, but the plugin for the coverage reporter (karma-coverage) is installed through the dependency specified in the [package.json](svc/test/unit/package.json) file. The coverage reporter will create a coverage report for every browser that the tests are run in. In addition, it will create a JSON file that outputs the intermediate data.

*coverage-reporter*: defines the type of report output and where the reports will be stored.

*singleRun*: If true, Karma will start and capture all configured browsers, run tests and then exit with an exit code of 0 or 1 depending on whether all tests passed or any tests failed.

**[package.json](svc/test/unit/package.json)**

The package.json file defines the following properties:

*name*: the name of the module.

*version*: the version of the module.

*description*: this enables people to discover your package as it's listed in npm search.

*scripts*: a dictionary / map of script commands, with the key being the event and the value being the command to run for that event. The seed defines the script event "test" which executes the ./node_modules/.bin/karma start command.

*devDependencies*: dependencies that don't need to be downloaded by consumers of the module, but are needed for the development of the module.


#### 4.2.3 Test structure ####

For unit testing of our Angular javascript components, the following convention has been followed.

For each javascript component that we unit test in the svc/public/webapp folder, a corresponding jasmine unit test component has been created in an identically named folder under the svc/test/unit folder

e.g. to test the svc/public/webapp/feature/login/login-controller.js component, there is a corresponding jasmine test file login-controller.test.html located in the svc/test/unit/feature/login/ folder.

Each test has an html file defining the files required to run the individual tests and a javascript file containing the jasmine unit tests to be executed.

See [login-controller.test.html](svc/test/unit/feature/login/login-controller.test.html) for an example of an html file defining the test dependencies, and [login-controller.spec.js](svc/test/unit/feature/login/login-controller.spec.js) for example jasmine test scripts.


#### 4.2.4 Test Execution ####


**Running from command line**

To run the jasmine tests with the karma test runner, open a command window at the svc/test/unit folder.

Enter the following command

     npm run test

This will invoke the test "event" in the [package.json](svc/test/unit/package.json) file, which runs the command "./node_modules/.bin/karma start"

Any unsuccessful tests will be displayed on the command window, with details of the test that failed and the name of the jasmine file. 

**Running as part of project build**

The [build.sbt](build.sbt) file has been configured to invoke the karma test runner to execute the jasmine unit tests as part of the sbt test task.

The [Build.scala](project/Build.scala) file contains the definition for this client test task

To run the karma tests open a command window at the project root and enter the following command

    sbt test

This will invoke the Play unit tests as well as the Angular client unit tests.


### 4.3 Play Unit Testing ###

The PAMM seed folder structure adheres to the Play application convention, so in order for unit tests in the Play application to be invoked as part of the "sbt test" task, simply follow the instructions as detailed on the [Play Framework Testing page](https://www.playframework.com/documentation/2.4.3/JavaTest). 


5. Running the Application
----------

Open a command window at the project root folder and enter the following command

    activator

Once the > prompt is displayed, enter the following to navigate to the svc project

    project svc

Once the [svc] $ prompt is displayed enter the following command

    run

Once the "Server started" message is displayed in the command window, access the following URL in your browser

    http://localhost:9000

The PAMM login page should be presented. The seed has no authentication configured for this initial draft version, so by entering any username and password, the PAMM dashboard should be displayed. The angular client is integrated with the Play backend and any actions on the Angular client will be routed to the Play backend. 

**Note**: Projects added via the "Add New Project" feature of the PAMM application will be stored in an embedded in-memory H2 database. This initial version of the seed has no permanent persistence configured and any data stored in the H2 database will be lost when activator is stopped by entering the following command at the [svc] $ prompt

    exit




