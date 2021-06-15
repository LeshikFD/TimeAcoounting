package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.StatusDAO;
import com.my.project.model.Status;
import com.my.project.sql.SQLCommands;

public class StatusDAOImpl implements StatusDAO {
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);

	@Override
	public List<Status> getAllStatuses() throws SQLException {
		List<Status> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.GET_ALL_STATUSES);
			while (rs.next()) {
				Status status = new Status();
				status.setId(rs.getInt("id"));
				status.setName(rs.getString("name"));
				list.add(status);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all statuses", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
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

}
