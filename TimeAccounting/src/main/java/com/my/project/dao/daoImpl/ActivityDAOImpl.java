package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.ActivityDAO;
import com.my.project.model.Activity;
import com.my.project.sql.SQLCommands;

public class ActivityDAOImpl implements ActivityDAO {
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);

	@Override
	public boolean addNewActivity(Activity activity) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.ADD_NEW_ACTIVITY);
			int k = 1;
			ps.setString(k++, activity.getName());
			ps.setString(k++, activity.getLastDate());
			ps.setInt(k++, activity.getTimeCount());
			ps.setInt(k++, activity.getCategoryId());
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException("Can't create activity", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return true;
	}

	@Override
	public boolean deleteActivityById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.DELETE_ACTIVITY);
			int k = 1;
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Can't delete activity", e);
		} finally {
			close(ps);
			cp.returnConnection(con);
		}
		return true;
	}

	@Override
	public int getActivityId(Activity activity) throws SQLException {
		int id = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.GET_ACTIVITY_ID);
			int k = 1;
			ps.setString(k++, activity.getName());
			ps.setString(k++, activity.getLastDate());
			ps.setInt(k++, activity.getTimeCount());
			ps.setInt(k++, activity.getCategoryId());
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id");
		} catch (SQLException e) {
			throw new SQLException("Can't get activityId", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return id;
	}

	@Override
	public Activity findActivityById(int id) throws SQLException {
		Activity activity = new Activity();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.FIND_ACTIVITY_BY_ID);
			int k = 1;
			ps.setInt(k++, id);
			rs = ps.executeQuery();
			rs.next();
			activity = setInActivity(rs);
		} catch (SQLException e) {
			throw new SQLException("Can't find activity", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return activity;
	}

	@Override
	public boolean updateActivityByTime(Activity activity, int timeCount) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.UPDATE_ACTIVITY);
			int k = 1;
			ps.setString(k++, getCurrentDateTime());
			ps.setInt(k++, (activity.getTimeCount() + timeCount));
			ps.setInt(k++, activity.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Can't update activity", e);
		} finally {
			close(ps);
			cp.returnConnection(con);
		}
		return true;
	}

	@Override
	public List<Activity> getAllActivities() throws SQLException {
		List<Activity> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.GET_ALL_ACTIVITIES);
			while (rs.next()) {
				Activity activity = setInActivity(rs);
				list.add(activity);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public List<Activity> getAllActivityByNameAsc() throws SQLException {
		List<Activity> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.ORDER_ACTIVITY_BY_NAME);
			while (rs.next()) {
				Activity activity = setInActivity(rs);
				list.add(activity);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by name int natural order", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public List<Activity> getAllActivityByNameDesc() throws SQLException {
		List<Activity> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.REVERSE_ORDER_ACTIVITY_BY_NAME);
			while (rs.next()) {
				Activity activity = setInActivity(rs);
				list.add(activity);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by name int reverse order", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public List<Activity> getActivityListByCategory(int categoryId) throws SQLException {
		List<Activity> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQLCommands.GET_ACTIVITY_BY_CATEGORY);
			int k = 1;
			ps.setInt(k++, categoryId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Activity activity = setInActivity(rs);
				list.add(activity);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by category", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return list;
	}

	private Activity setInActivity(ResultSet rs) throws SQLException {
		Activity activity = new Activity();
		activity.setId(rs.getInt("id"));
		activity.setName(rs.getString("name"));
		activity.setLastDate(rs.getString("last_date"));
		activity.setTimeCount(rs.getInt("timecount"));
		activity.setCategoryId(rs.getInt("category_id"));
		return activity;
	}

	@Override
	public List<Activity> getAllActivityByCategDesc() throws SQLException {
		List<Activity> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.REVERSE_ORDER_ACTIVITY_BY_CATEG);
			while (rs.next()) {
				Activity activity = setInActivity(rs);
				list.add(activity);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by category int reverse order", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public List<Activity> getAllActivityByCategAsc() throws SQLException {
		List<Activity> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.ORDER_ACTIVITY_BY_CATEG);
			while (rs.next()) {
				Activity activity = setInActivity(rs);
				list.add(activity);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by category int natural order", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	private String getCurrentDateTime() {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		Date date = new Date(currentTime);
		return simpleDateFormat.format(date);
	}

	@Override
	public List<Integer> getListOfOrderCountAct(boolean order) throws SQLException {
		List<Integer> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			if (order == true) {
				rs = st.executeQuery(SQLCommands.ORDER_COUNT_ACTIVITY_OF_CATEGROY);
			} else {
				rs = st.executeQuery(SQLCommands.DESC_ORDER_COUNT_ACTIVITY_OF_CATEGROY);
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				list.add(id);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all category id in natural order", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public List<Activity> getAllActivityByUserCount(boolean order) throws SQLException {
		List<Activity> list = new ArrayList<>();
		List<Integer> listId = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			if (order == true) {
				listId = getListOfOrderCountAct(true);
			} else {
				listId = getListOfOrderCountAct(false);
			}
			for (int categoryId : listId) {
				ps = con.prepareStatement(SQLCommands.GET_ACTIVITY_BY_CATEGORY);
				int k = 1;
				ps.setInt(k++, categoryId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Activity activity = setInActivity(rs);
					list.add(activity);
				}
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by User Count", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public int getActivityCount() throws SQLException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = cp.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.COUNT_ALL_ACTIVITIES);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw new SQLException("Can't count activities", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return count;
	}

	private void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	private void close(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	private void close(PreparedStatement ps) throws SQLException {
		if (ps != null) {
			ps.close();
		}
	}
}
