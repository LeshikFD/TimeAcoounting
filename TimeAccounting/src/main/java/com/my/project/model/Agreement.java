package com.my.project.model;

public class Agreement {
	private int usersId;
	private int activityId;
	private int statusId;

	public Agreement() {
		
	}
	
	public Agreement(int usersId, int activityId) {
		super();
		this.usersId = usersId;
		this.activityId = activityId;
	}
	
	public Agreement(int usersId, int activityId, int statusId) {
		super();
		this.usersId = usersId;
		this.activityId = activityId;
		this.statusId = statusId;
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
	
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityId;
		result = prime * result + statusId;
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
		Agreement other = (Agreement) obj;
		if (activityId != other.activityId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agreement [usersId=" + usersId + ", activityId=" + activityId + ", statusId=" + statusId + "]";
	}
	
}
