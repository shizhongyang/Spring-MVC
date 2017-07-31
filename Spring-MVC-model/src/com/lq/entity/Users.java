package com.lq.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import antlr.Token;


@Entity
@Table(name = "T_USERS")
public class Users {
	
	private String token;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;


	@Column(name="name")
	private String name;
	
	
	
	@Column(name = "password")
	private String password;
	// Ê¡ÂÔget set·½·¨

	
	@Column(name="age")
	private Integer age;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="birth")
	private Date birth;
	
	
	@Email
	@Column(name="email")
	private String email;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Users [token=" + token + ", id=" + id + ", name=" + name + ", password=" + password + ", age=" + age
				+ ", phone=" + phone + ", imageUrl=" + imageUrl + ", birth=" + birth + ", email=" + email + "]";
	}


	
}
