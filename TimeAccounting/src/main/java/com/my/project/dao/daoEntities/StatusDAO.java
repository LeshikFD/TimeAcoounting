package com.my.project.dao.daoEntities;

import java.sql.SQLException;
import java.util.List;

import com.my.project.model.Status;

public interface StatusDAO {
	List<Status> getAllStatuses() throws SQLException;
}
