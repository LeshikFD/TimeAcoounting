package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.AgreementDAO;
import com.my.project.model.Activity;
import com.my.project.model.Agreement;
import com.my.project.model.Users;
import com.my.project.sql.SQLCommands;

public class AgreementDAOImpl implements AgreementDAO {
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);
	@Override
	public boolean addNewAgreement(int userId, int activityId, int statusId) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.ADD_NEW_AGREEMENT);
			int k = 1;
			ps.setInt(k++, userId);
			ps.setInt(k++, activityId);
			ps.setInt(k++, statusId);
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException("Can't create agreement", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return true;
	}

	@Override
	public Map<Activity, Users> getAgreementsMap() throws SQLException {
		Map<Activity, Users> map = new HashMap<Activity, Users>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.GET_ALL_AGREEMENTS);
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
			throw new SQLException("Can't get all agreements", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return map;
	}

	@Override
	public List<Agreement> getAllAgreementsList() throws SQLException {
		List<Agreement> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.GET_ALL_AGREEMENTS);
			while (rs.next()) {
				Agreement agreement = new Agreement();
				agreement.setActivityId(rs.getInt("activity_id"));
				agreement.setUsersId(rs.getInt("users_id"));
				agreement.setStatusId(rs.getInt("status_id"));
				list.add(agreement);
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
	public Agreement getAgreementByActivityId(int activityId) throws SQLException {
		Agreement agreement = new Agreement();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.FIND_AGREEMENT_BY_ID);
			int k = 1;
			ps.setInt(k++, activityId);
			rs = ps.executeQuery();
			rs.next();
			agreement.setUsersId(rs.getInt("users_id"));
			agreement.setActivityId(rs.getInt("activity_id"));
		} catch (SQLException e) {
			throw new SQLException("Can't get agreement", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return agreement;
	}

	@Override
	public boolean deleteAgreement(Agreement agreement) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.DELETE_AGREEMENT);
			int k = 1;
			ps.setInt(k++, agreement.getUsersId());
			ps.setInt(k++, agreement.getActivityId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Can't delete agreement", e);
		} finally {
			close(ps);
			cp.returnConnection(con);
		}
		return true;
	}

	@Override
	public int countAllAgreementsByStatus(int statusId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.COUNT_ALL_AGREEMENTS_BY_STATUS);
			int k = 1;
			ps.setInt(k++, statusId);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw new SQLException("Can' count agreements by status id", e);
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

	private void close(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}
}
