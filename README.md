Cassandra/JBoss Web App
================================


Introduction
-------------------------

This example shows how to deploy a web application in jboss when data are retrieved from cassandra database through DataStax Java Driver.


Getting Started
-------------------------

To get started with the project run from CQL Shell the script 'setup.cql'.

To build and deploy the project use the following command:

	mvn clean package jboss-as:deploy
	
After the project has been deployed, just open your web browser and open the following url:
http://localhost:8080/webapp


Tricks
-------------------------

DataStax Java Driver uses as dependencies Netty and Guava.

This would generate some issues during deployments.

The first trick is to exclude netty while import datastax java driver dependency. Netty is already provided by the container so it's unuseful.

But at runtime, when the driver performs select query it would be returned the following error message:

	java.lang.NoClassDefFoundError: org/jboss/netty/channel/ChannelFactory
	
Adding a new dependency for netty solves the issue.

DataStax Java Driver uses Guava 15.0. This could generate issues if your project uses CDI 1.0.

So add a new dependency for latest Guava maven repositories.

In this example I used Guava 16-rc1 that was released to solve this issue.


Face to Face with Cassandra
-------------------------

Using cassandra is necessary to take in mind what are the characteristics of the project you are developing.

Multiple requests generates multiple queries at same time, so you need to avoid anti-patterns due to continues Read/Write operations.

In UserData.java you will find this piece of code:

	// To prevent anti-pattern, prepare the statement once
	private static PreparedStatement selectStatement;
	
Prepare the statement just once. PreparedStatement is thread-safe so it could be shared safely by multiple requests.