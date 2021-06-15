package com.my.project.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.my.project.sql.SQLCommands;

public class DBConnectionPoolTest {
	@Test
	public void dbConnectionPoolConstructorsTest() {
		DBConnectionPool dbcp = new DBConnectionPool("t", "t", "t", 5);
		DBConnectionPool dbcp1 = new DBConnectionPool("test");
		Assert.assertNotEquals(null, dbcp);
		Assert.assertNotEquals(null, dbcp1);
	}
	
	@Test
	public void dbConnectionPoolConstructorsExceptionTest() throws SQLException {
		DBConnectionPool dbcp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 2);
		new DBConnectionPool("test");
		dbcp.getConnection();
	}
	
	@Test
	public void dbConnectionPoolGetConnectionTest() throws SQLException {
		DBConnectionPool dbcp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 2);
		new DBConnectionPool("com.mysql.cj.jdbc.Driver");
		Connection con = dbcp.getConnection();
		Assert.assertNotEquals(null, con);		
		dbcp.getConnection();
	}
	
	@Test(expected = SQLException.class)
	public void dbConnectionPoolGetConnectionExceptionTest() throws SQLException {
		DBConnectionPool dbcp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 0);
		dbcp.getConnection();
	}
	
	@Test(expected = NullPointerException.class)
	public void dbConnectionPoolReturnConnectionNPEExceptionTest() throws SQLException {
		DBConnectionPool dbcp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 0);
		dbcp.returnConnection(null);
	}
	
	@Test(expected = SQLException.class)
	public void dbConnectionPoolReturnConnectionTest() throws SQLException {
		DBConnectionPool dbcp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 1);
		Connection con = dbcp.getConnection();
		dbcp.returnConnection(con);
		con = dbcp.getConnection();
		DBConnectionPool dbcp1 = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 1);
		dbcp1.returnConnection(con);
	}
}
