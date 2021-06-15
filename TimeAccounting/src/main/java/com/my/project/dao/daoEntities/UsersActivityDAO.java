package com.my.project.dao.daoEntities;

import java.sql.SQLException;
import java.util.Map;

import com.my.project.model.Activity;
import com.my.project.model.Users;

public interface UsersActivityDAO {
	Map<Activity, Users> getAllUserActByIdMap(int userId) throws SQLException;
	boolean assignActivityForUser(int activityId, int userId) throws SQLException;
	int countAllUserActsById(int userId) throws SQLException;
}
