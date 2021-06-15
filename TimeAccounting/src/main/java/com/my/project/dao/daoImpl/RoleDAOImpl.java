package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.RoleDAO;
import com.my.project.model.Roles;
import com.my.project.sql.SQLCommands;

public class RoleDAOImpl implements RoleDAO {
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);

	@Override
	public String getRoleNameById(int id) throws SQLException {
		Roles role = new Roles();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.GET_ROLE_NAME_BY_ID);
			int k = 1;
			ps.setInt(k, id);
			rs = ps.executeQuery();
			rs.next();
			role.setName(rs.getString("name"));
		} catch (SQLException e) {
			throw new SQLException("Can't get role name by id", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return role.getName();
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
