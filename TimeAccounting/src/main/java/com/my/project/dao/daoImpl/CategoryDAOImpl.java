package com.my.project.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.project.connection.DBConnectionPool;
import com.my.project.dao.daoEntities.CategoryDAO;
import com.my.project.model.Category;
import com.my.project.sql.SQLCommands;

public class CategoryDAOImpl implements CategoryDAO {
	private DBConnectionPool cp = new DBConnectionPool(SQLCommands.URL, SQLCommands.USER, SQLCommands.PASSWORD, 20);
	@Override
	public List<Category> getAllCategories() throws SQLException {
		List<Category> list = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = cp.getConnection();
			con.setAutoCommit(false);
			st = con.createStatement();
			rs = st.executeQuery(SQLCommands.GET_ALL_CATEGORIES);
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				list.add(category);
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException("Can't get all categories", e);
		} finally {
			close(rs);
			close(st);
			cp.returnConnection(con);
		}
		return list;
	}

	@Override
	public boolean addNewCategory(Category category) throws SQLException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.ADD_NEW_CATEGORY);
			int k = 1;
			ps.setString(k++, category.getName());
			ps.setString(k++, category.getDescription());
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException("Can't create new category", e);
		} finally {
			close(rs);
			close(ps);
			cp.returnConnection(con);
		}
		return true;
	}

	@Override
	public boolean deleteCategoryById(int id) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = cp.getConnection();
			ps = con.prepareStatement(SQLCommands.DELETE_CATEGORY_BY_ID);
			int k = 1;
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Can't delete category by id", e);
		}finally {
			close(ps);
			cp.returnConnection(con);
		}
		return true;
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
