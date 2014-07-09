package com.qubaopen.survey.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qubaopen.survey.entity.util.AbstractBaseEntity;

/**
 * @author mars 用户表
 */
@Entity
@Table(name = "sys_user")
public class User extends AbstractBaseEntity<Long> {

	private static final long serialVersionUID = -6865482202586788603L;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 电话
	 */
	@Column(unique = true, nullable = false, length = 11)
	private String phone;

	/**
	 * 邮箱
	 */
	@Column(unique = true)
	private String email;

	/**
	 * 是否激活
	 */
	private boolean isActivated;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

}
