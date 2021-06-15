package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.UsersActivityDAO;
import com.my.project.model.Activity;
import com.my.project.model.Users;
import com.my.project.sql.SQLCommands;

public class UsersActivityDAOImpl implements UsersActivityDAO {
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);

	@Override
	public Map<Activity, Users> getAllUserActByIdMap(int userId) throws SQLException {
		Map<Activity, Users> map = new HashMap<Activity, Users>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQLCommands.GET_ALL_USERACTS_BY_UID);
			int k = 1;
			ps.setInt(k++, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				UsersDAOImpl ud = new UsersDAOImpl();
				ActivityDAOImpl ad = new ActivityDAOImpl();
				Users user = ud.findUserById(rs.getInt("users_id"));
				Activity activity = ad.findActivityById(rs.getInt("activity_id"));
				map.put(activity, user);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all activities by user id", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return map;
	}

	@Override
	public boolean assignActivityForUser(int activityId, int userId) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.ADD_NEW_USERACTIVITY);
			int k = 1;
			ps.setInt(k++, activityId);
			ps.setInt(k++, userId);
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
	public int countAllUserActsById(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.COUNT_ALL_USERACTS_BY_UID);
			int k = 1;
			ps.setInt(k++, userId);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw new SQLException("Can't count user activities by user id", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return count;
	}

	private void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	private void close(PreparedStatement ps) throws SQLException {
		if (ps != null) {
			ps.close();
		}
	}
}
