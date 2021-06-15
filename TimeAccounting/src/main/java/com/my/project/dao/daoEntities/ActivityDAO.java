package com.my.project.dao.daoEntities;

import java.sql.SQLException;
import java.util.List;

import com.my.project.model.Activity;

public interface ActivityDAO {
	boolean addNewActivity(Activity activity) throws SQLException;
	boolean deleteActivityById(int id) throws SQLException;
	int getActivityId (Activity activity) throws SQLException;
	Activity findActivityById(int id) throws SQLException;
	boolean updateActivityByTime(Activity activity, int timeCount) throws SQLException;
	List<Activity> getAllActivities() throws SQLException;
	List<Activity> getAllActivityByNameDesc() throws SQLException;
	List<Activity> getAllActivityByNameAsc() throws SQLException;;
	List<Activity> getActivityListByCategory(int categoryId) throws SQLException;
	List<Activity> getAllActivityByCategDesc() throws SQLException;
	List<Activity> getAllActivityByCategAsc() throws SQLException;
	List<Integer> getListOfOrderCountAct(boolean order) throws SQLException;
	List<Activity> getAllActivityByUserCount(boolean order) throws SQLException;
	int getActivityCount() throws SQLException;
}
