/*
 * This Java class is designed to model a user. Each user has a username, password, and age.
 */
package model;

public class User {

	// Each user will be able to create their own username, which must be
	// unique.
	private String username = "";

	// Each user can create their own password as well. Passwords do not need to
	// be unique.
	private String password = "";

	// In addition, each user must enter their current age to verify that they
	// are old enough to use SMART.
	private int age = 0;
	
	private boolean host = false;

	public User(String username, String password, int age, boolean host) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.setHost(host);
	}
	
	public boolean isUsername(String username) {
		if (username.equals(this.username)) {
			return true;
		}
		return false;
	}
	
	public boolean isPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

}