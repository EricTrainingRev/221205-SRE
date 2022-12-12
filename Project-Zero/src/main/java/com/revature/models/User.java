package com.revature.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	/*
	 * the lombok dependency will generate getters and setters for the fields below at runtime
	 */

	private int id;
	private String username;
	private String password;

}
