package com.cherrypicks.devops.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable{
	
	private static final long serialVersionUID = 6436574385698307238L;
	
	private String username;
	
	private String password;

}
