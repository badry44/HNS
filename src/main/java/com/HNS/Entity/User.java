package com.HNS.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class User {
	  @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer id;
private String userName;
private String password;
private int age;
private String email;
private int userType;
public User()
{
	userName = "";
	password = "";
	age = 0;
	email = "";
	userType=1;
}
public User(String _UserName,String _Password,int _Age,String _Email)
{
	userName = _UserName;
	password = _Password;
	age = _Age;
	email = _Email;
	userType=1;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getUserType() {
	return userType;
}
public void setUserType(int userType) {
	this.userType = userType;
}
}