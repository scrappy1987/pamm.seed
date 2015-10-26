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

    1.  Controllers

    2.  Business Service Layer

    3.  Data Persistence Layer

**3. Angular Component**

**4. Testing**

    1.  Javascript Unit Testing

    2.  Java Unit Testing

    3.  End to End Testing

1. Architecture
---------------

The following diagram shows the high level reference architecture for the application: ![](./media/image1.gif)

2. Play Component
-----------------

The Play component of the PAMM seed consists of the following component layers:

**1. Controllers**

The Controller layer exposes the applications RESTful API to clients, facilitated by the [Play frameworks routing mechanism](https://www.playframework.com/documentation/2.4.3/JavaRouting). Each controller exposes a RESTful API for a single application resource.

The Controllers responsibility is to accept requests to a resource and then delegate the processing of that request to a Business Service Layer component. The Transactional boundary for the processing of a request is defined on the Controller.![](./media/image2.gif)

**2. Business Service Layer**

The Business Service layer services provide a façade style interface to a number of underlying service operations. Each service is an aggregation of service operations, with the service operations providing the business logic to maintain application resources.

Each service operation should inherit from the ServiceOperation superclass, which should contain the system "cross cutting" behaviour (e.g. application level authentication and authorization, audit, error handling)

**3. Data Persistence Layer**

The Data / Persistence layer provides a persistence mechanism and its associated model

3. Angular Component
--------------------

In Development

4. Testing
----------

In Development
