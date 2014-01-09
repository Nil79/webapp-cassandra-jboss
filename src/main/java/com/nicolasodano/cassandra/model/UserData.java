package com.nicolasodano.cassandra.model;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

/**
 * Data access object to manage CRUD operations with table user
 * 
 * @author Nicola Sodano
 *
 */
public class UserData extends Data {
	
	// To prevent anti-pattern, prepare the statement once
	private static PreparedStatement selectStatement;
	
	private String name;
	private String lastname;
	private String email;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private UserData(Row row) {
		this.name = row.getString("name");
		this.lastname = row.getString("lastname");
		this.email = row.getString("email");
		this.password = row.getString("password");
	}
	
	public UserData(String name, String lastname, String email, String password) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
	public static List<UserData> findAll() {
		ResultSet rs = getSession().execute("SELECT * FROM user");
		List<UserData> result = new ArrayList<UserData>();
		for(Row row: rs) {
			result.add(new UserData(row));
		}
		return result;
	}
	
	public static UserData findByPrimaryKey(String email) {
		if(selectStatement == null) {
			selectStatement = getSession().prepare("SELECT * FROM user WHERE email = ?");
		}
		
		BoundStatement bs = selectStatement.bind(email);
		ResultSet rs = getSession().execute(bs);
		if(rs.isExhausted()) {
			return null;
		}
		return new UserData(rs.one());		
	}
	

}
