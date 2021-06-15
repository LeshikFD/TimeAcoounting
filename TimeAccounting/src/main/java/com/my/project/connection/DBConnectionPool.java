package com.my.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

public class DBConnectionPool {
	private String url;
	private String user;
	private String password;
	private int maxPoolSize = 20;
	private int connNum = 0;
	private static String classForName = "";
	private static final String SQL_VERIFYCONN = "select 1";

	Stack<Connection> freePool = new Stack<>();
	Stack<Connection> occupiedPool = new Stack<>();

	/**
	 * This constructor get name that initialize local static field
	 * 
	 * @param classForName - variable that contains name of class which provides
	 *                     forcibly connection to the database
	 */
	public DBConnectionPool(String classForName) {
		DBConnectionPool.classForName = classForName;
	}

	/**
	 * This constructor initialize local field
	 * 
	 * @param url      - it's a name of path to database
	 * @param user     - it's a name of user who want to connect to database
	 * @param password - it's user's password
	 * @param maxSize  - it's a value of max pool size
	 */
	public DBConnectionPool(String url, String user, String password, int maxPoolSize) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.maxPoolSize = maxPoolSize;
	}

	/**
	 * Method for getting connection object. Firstly it checks for pool loading.
	 * After that it trying to get connection from private method
	 * getConnectionFromPool(). If returned connection was empty it trying create
	 * new connection for pool and take it. Last operation is trying to make the
	 * connection available, after that return free connection
	 * 
	 * @return - free connection object
	 * @throws SQLException - SQL exception that throwing when connection pool is
	 *                      overloading
	 */
	public synchronized Connection getConnection() throws SQLException {
		Connection conn = null;
		if (isFull()) {
			throw new SQLException("The connection pool is overload.");
		}
		conn = getConnectionFromPool();

		// If there is no free connection
		if (conn == null) {
			conn = createNewConnectionForPool();
		}

		// The connection is lost. By this, connection may be
		// active. Otherwise reconnect it.
		conn = makeAvailable(conn);
		return conn;
	}

	/**
	 * Method that checks for overloading connection pool
	 * 
	 * @return - true, if connection is full, otherwise - false
	 */
	private synchronized boolean isFull() {
		return ((freePool.size() == 0) && (connNum >= maxPoolSize));
	}

	/**
	 * Method that takes current pool and trying to get connection from it when it's
	 * possible.
	 * 
	 * @return - connection object if connection pool has one, otherwise returns -
	 *         null
	 */
	private Connection getConnectionFromPool() {
		Connection conn = null;
		if (freePool.size() > 0) {
			conn = freePool.pop();
			occupiedPool.add(conn);
		}
		return conn;
	}

	/**
	 * Method create new connection for pool and return it. This connection isn't
	 * necessarily free but new.
	 * 
	 * @return - new connection object
	 * @throws SQLException - SQL exception when connection couldn'b be created
	 */
	private Connection createNewConnectionForPool() throws SQLException {
		Connection conn = createNewConnection();
		connNum++;
		occupiedPool.add(conn);
		return conn;
	}

	/**
	 * Method that creates new connection
	 * 
	 * @return - new connection object
	 * @throws SQLException - SQL which throwing when connection creating is failed
	 */
	private Connection createNewConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(classForName);
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	/**
	 * Method that realizes current connection and returning it to pool
	 * 
	 * @param conn - connection which will be realized
	 * @throws SQLException - SQL exception when connection is returned already or
	 *                      it isn't for this pool
	 */
	public synchronized void returnConnection(Connection conn) throws SQLException {
		if (conn == null) {
			throw new NullPointerException();
		}
		if (!occupiedPool.remove(conn)) {
			throw new SQLException("The connection is returned already or it isn't for this pool");
		}
		freePool.push(conn);
	}

	/**
	 * Method that trying to make connection available. If connection is available
	 * if returning as object, otherwise method removing connection from occupied
	 * pool and than forcibly closes. After that it creates new one and returning it
	 * 
	 * @param conn - current connection object
	 * @return - new free connection object
	 * @throws SQLException
	 */
	private Connection makeAvailable(Connection conn) throws SQLException {
		if (isConnectionAvailable(conn)) {
			return conn;
		}

		// If the connection is't available, reconnect it.
		occupiedPool.remove(conn);
		connNum--;
		conn.close();

		conn = createNewConnection();
		occupiedPool.add(conn);
		connNum++;
		return conn;
	}

	/**
	 * Method that checks for connection availability
	 * 
	 * @param conn - checking connection
	 * @return - true if connection is available, otherwise - false
	 */
	private boolean isConnectionAvailable(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeQuery(SQL_VERIFYCONN);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}