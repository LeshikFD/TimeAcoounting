package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.UsersDAO;
import com.my.project.model.Users;
import com.my.project.sql.SQLCommands;

public class UsersDAOImpl implements UsersDAO{
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);
	@Override
	public List<Users> getAllUsers() throws SQLException {
		List<Users> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.GET_ALL_USERS);
			while (rs.next()) {
				Users user = setInUser(rs);
				list.add(user);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all Users", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public Users findUserById(int id) throws SQLException {
		Users user = new Users();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.FIND_USER_BY_ID);
			int k = 1;
			ps.setInt(k++, id);		
			rs = ps.executeQuery();
			rs.next();
			user = setInUser(rs);
		} catch (SQLException e) {
			throw new SQLException("Can't find user by id", e);
		}finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return user;
	}

	@Override
	public Users findLogInUser(String login, String password) throws SQLException {
		Users user = new Users();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.FIND_LOG_IN_USER);
			int k = 1;
			ps.setString(k++, login);		
			ps.setString(k++, password);		
			rs = ps.executeQuery();
			rs.next();
			user = setInUser(rs);
		} catch (SQLException e) {
			throw new SQLException("Can't find user in system", e);
		}finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return user;
	}
	
	private Users setInUser(ResultSet rs) throws SQLException {
		Users user = new Users();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setRoleId(rs.getInt("roles_id"));
		user.setLocale(rs.getString("locale"));
		return user;
	}
	
	@Override
	public Users changeUserLocale(String locale, int id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		Users user = new Users();
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.UPDATE_USER_LOCALE);
			int k = 1;
			ps.setString(k++, locale);
			ps.setInt(k++, id);
			ps.executeUpdate();
			user = findUserById(id);
		} catch (SQLException e) {
			throw new SQLException("Can't update user locale", e);
		}finally {
			close(ps);
			cp.returnConnection(con);
		}
		return user;
	}

	
	private void close(ResultSet rs) throws SQLException {
		if(rs != null) {
			rs.close();
		}
	}
	
	private void close(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}
	
	private void close(PreparedStatement ps) throws SQLException {
		if(ps != null) {
			ps.close();
		}
	}
}
