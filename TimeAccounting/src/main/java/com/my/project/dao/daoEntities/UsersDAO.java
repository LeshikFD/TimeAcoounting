package com.my.project.dao.daoEntities;

import java.sql.SQLException;
import java.util.List;

import com.my.project.model.Users;

public interface UsersDAO {
	List<Users> getAllUsers() throws SQLException;
	Users findUserById(int id) throws SQLException;
	Users findLogInUser(String login, String password) throws SQLException;
	Users changeUserLocale (String locale, int id) throws SQLException;
}
