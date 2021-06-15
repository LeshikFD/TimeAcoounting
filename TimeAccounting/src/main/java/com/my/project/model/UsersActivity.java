package com.my.project.model;

public class UsersActivity {
	private int usersId;
	private int activityId;

	public UsersActivity() {
		
	}
	
	public UsersActivity(int usersId, int activityId) {
		super();
		this.usersId = usersId;
		this.activityId = activityId;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityId;
		result = prime * result + usersId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersActivity other = (UsersActivity) obj;
		if (activityId != other.activityId)
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsersActivity [usersId=" + usersId + ", activityId=" + activityId + "]";
	}

	
}
