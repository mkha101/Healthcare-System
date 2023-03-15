package model;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String title;
	private String employeeId;
	private static int EmIdCounter = 0;

	public User(String username, String password, String firstName, String lastName, String title) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.employeeId = String.valueOf(EmIdCounter++);
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				"employeeID='" + employeeId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", title='" + title + '\'' +
				'}';
	}

	@Override
	public int compareTo(User o) {
		return this.username.compareTo(o.username);
	}

}
