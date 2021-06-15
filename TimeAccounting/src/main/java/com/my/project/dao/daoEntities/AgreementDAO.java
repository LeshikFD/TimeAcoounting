package com.my.project.dao.daoEntities;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.my.project.model.Activity;
import com.my.project.model.Agreement;
import com.my.project.model.Users;

public interface AgreementDAO {
	boolean addNewAgreement(int userId, int activityId, int statusId) throws SQLException;
	Map<Activity, Users> getAgreementsMap() throws SQLException;
	List<Agreement> getAllAgreementsList() throws SQLException;
	Agreement getAgreementByActivityId(int activityId) throws SQLException;
	boolean deleteAgreement(Agreement agreement) throws SQLException;
	int countAllAgreementsByStatus(int statusId) throws SQLException;
}
