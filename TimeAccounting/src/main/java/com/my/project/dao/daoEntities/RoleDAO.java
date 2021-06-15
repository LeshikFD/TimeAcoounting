package com.my.project.dao.daoEntities;

import java.sql.SQLException;

public interface RoleDAO {
	String getRoleNameById(int id) throws SQLException;
}
