package com.example.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tblUserInfo")
public class UserInfo {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	
	@Column(name="username")
	private String username;
	
	
	@Column(name="password")
	private String password;
	
	@OneToMany
	private Collection<Address> address = new ArrayList<Address>();
	
	public UserInfo() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Collection<Address> getAddress() {
		return address;
	}


	public void setAddress(Collection<Address> address) {
		this.address = address;
	}


	
	
	
}
