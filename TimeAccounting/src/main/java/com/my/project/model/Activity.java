package com.my.project.model;

public class Activity {
	private int id;
	private String name;
	private String lastDate;
	private int timeCount;
	private int categoryId;

	public Activity() {

	}

	public Activity(String name, String lastDate, int timeCount, int categoryId) {
		super();
		this.name = name;
		this.lastDate = lastDate;
		this.timeCount = timeCount;
		this.categoryId = categoryId;
	}

	public Activity(int id, String name, String lastDate, int timeCount, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.lastDate = lastDate;
		this.timeCount = timeCount;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", lastDate=" + lastDate + ", timeCount=" + timeCount
				+ ", categoryId=" + categoryId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + timeCount;
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
		Activity other = (Activity) obj;
		if (categoryId != other.categoryId)
			return false;
		if (lastDate != other.lastDate)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timeCount != other.timeCount)
			return false;
		return true;
	}

}
