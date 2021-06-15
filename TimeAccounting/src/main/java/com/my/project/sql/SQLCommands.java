package com.my.project.sql;

public class SQLCommands {
	private SQLCommands() {

	}

	public static final String URL = "jdbc:mysql://localhost:3306/timeacc";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	public static final String GET_ROLE_NAME_BY_ID = "SELECT name FROM timeacc.roles WHERE id=(?);";
	public static final String FIND_LOG_IN_USER = "SELECT * FROM timeacc.users WHERE BINARY login=(?) AND BINARY password=(?);";
	public static final String FIND_USER_BY_ID = "SELECT * FROM timeacc.users WHERE id=(?);";
	public static final String GET_ALL_CATEGORIES = "SELECT * FROM timeacc.category;";
	public static final String GET_ALL_AGREEMENTS = "SELECT * FROM timeacc.agreement;";
	public static final String GET_ALL_USERS = "SELECT * FROM timeacc.users WHERE roles_id!=2;";
	public static final String GET_ALL_USERSACTIVITIES = "SELECT * FROM timeacc.users_activity;";
	public static final String GET_ALL_ACTIVITIES = "SELECT * FROM timeacc.activity;";
	public static final String GET_ALL_STATUSES = "SELECT * FROM timeacc.status;";
	public static final String COUNT_ALL_AGREEMENTS_BY_STATUS = "SELECT count(*) FROM timeacc.agreement WHERE status_id=(?);";
	public static final String COUNT_ALL_ACTIVITIES = "SELECT count(*) FROM timeacc.activity;";
	public static final String COUNT_ALL_USERACTS_BY_UID = "SELECT count(*) FROM timeacc.users_activity WHERE users_id=(?);";
	public static final String ADD_NEW_ACTIVITY = "INSERT INTO timeacc.activity (name, last_date, timecount, category_id) VALUES(?, ?, ?, ?);";
	public static final String GET_ACTIVITY_ID = "SELECT id FROM timeacc.activity WHERE name=(?) AND last_date=(?) AND timecount=(?) AND category_id=(?);";
	public static final String ADD_NEW_AGREEMENT = "INSERT INTO timeacc.agreement (users_id, activity_id, status_id) VALUES(?, ?, ?);";
	public static final String ADD_NEW_USERACTIVITY = "INSERT INTO timeacc.users_activity (activity_id, users_id) VALUES(?, ?);";
	public static final String ADD_NEW_CATEGORY = "INSERT INTO timeacc.category (name, description) VALUES(?, ?);";
	public static final String FIND_ACTIVITY_BY_ID = "SELECT * FROM timeacc.activity WHERE id=(?);";
	public static final String FIND_AGREEMENT_BY_ID = "SELECT * FROM timeacc.agreement WHERE activity_id=(?);";
	public static final String DELETE_AGREEMENT = "DELETE FROM timeacc.agreement WHERE users_id=(?) AND activity_id=(?);";
	public static final String DELETE_CATEGORY_BY_ID = "DELETE FROM timeacc.category WHERE id=(?);";
	public static final String DELETE_ACTIVITY = "DELETE FROM timeacc.activity WHERE id=(?);";
	public static final String GET_ALL_USERACTS_BY_UID = "SELECT * FROM timeacc.users_activity WHERE users_id=(?);";
	public static final String UPDATE_ACTIVITY = "UPDATE timeacc.activity SET last_date = (?), timecount=(?) WHERE id=(?)";
	public static final String UPDATE_USER_LOCALE = "UPDATE timeacc.users SET locale = (?) WHERE id=(?)";
	public static final String ORDER_ACTIVITY_BY_NAME = "SELECT * FROM timeacc.activity ORDER BY name ASC;";
	public static final String REVERSE_ORDER_ACTIVITY_BY_NAME = "SELECT * FROM timeacc.activity ORDER BY name DESC;";
	public static final String GET_ACTIVITY_BY_CATEGORY = "SELECT * FROM timeacc.activity WHERE category_id=(?);";
	public static final String REVERSE_ORDER_ACTIVITY_BY_CATEG = "SELECT * FROM timeacc.activity ORDER BY category_id DESC;";
	public static final String ORDER_ACTIVITY_BY_CATEG = "SELECT * FROM timeacc.activity ORDER BY category_id ASC;";
	public static final String ORDER_COUNT_ACTIVITY_OF_CATEGROY = "SELECT count(category_id) count, c.id FROM timeacc.activity a JOIN timeacc.category c ON a.category_id=c.id GROUP BY category_id ORDER BY count;";
	public static final String DESC_ORDER_COUNT_ACTIVITY_OF_CATEGROY = "SELECT count(category_id) count, c.id FROM timeacc.activity a JOIN timeacc.category c ON a.category_id=c.id GROUP BY category_id ORDER BY count DESC;";

}
