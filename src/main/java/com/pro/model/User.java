package com.pro.model;

import lombok.Getter;
import lombok.Setter;

public class User {

	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String email;

	public User() {
	}

	public User(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
}