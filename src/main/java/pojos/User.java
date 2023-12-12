package pojos;

import java.sql.Date;

public class User {
	//id | first_name | last_name | email             | password | dob        | status | role
	private int id;
	private String fname,lname;
	private String email,pass;
	private Date dob;
	private boolean status;
	private String role;
	public User(int id, String fname, String lname, String email, String pass, Date dob, boolean status, String role) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.pass = pass;
		this.dob = dob;
		this.status = status;
		this.role = role;
	}
	
	public User(String firstName, String lastName, String email, String password, Date dob) {
		super();
		this.fname = firstName;
		this.lname = lastName;
		this.email = email;
		this.pass = password;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", pass=" + pass
				+ ", dob=" + dob + ", status=" + status + ", role=" + role + "]";
	}
	
	
	
	
}
