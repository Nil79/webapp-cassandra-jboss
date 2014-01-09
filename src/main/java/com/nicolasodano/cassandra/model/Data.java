package com.nicolasodano.cassandra.model;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

/**
 * Singleton class to istantiate one cassandra session that manages all incoming requests
 * @author Nicola Sodano
 *
 */
public class Data {
	
	private static Session session = null; // holds the cassandra session
	
	Data () { }
	
	/**
	 * Connects to the cluster and retrieve a cassandra session
	 * 
	 * @return a valid cassandra session
	 */
	public static Session getSession() {
		if (session == null) {
			//TODO should being parameterized
			Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
			session = cluster.connect("testks");
		}
		return session;
	}	
	
}
