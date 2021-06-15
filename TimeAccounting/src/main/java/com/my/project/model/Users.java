package com.my.project.model;

public class Users {
	private int id;
	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private int roleId;
	private String locale;

	public Users() {
		
	}
	
	public Users(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Users( String login, String passwrd, String firstname, String lastname, int roleId, String locale) {
		super();
		this.login = login;
		this.password = passwrd;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roleId = roleId;
		this.locale = locale;
	}
	
	public Users(int id, String login, String passwrd, String firstname, String lastname, int roleId, String locale) {
		super();
		this.id = id;
		this.login = login;
		this.password = passwrd;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roleId = roleId;
		this.locale = locale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwrd) {
		this.password = passwrd;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", login=" + login + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", roleId=" + roleId + ", locale=" + locale + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Users other = (Users) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	

}
