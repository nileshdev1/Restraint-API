/**
 * 
 */
package com.signup;

import java.io.Serializable;

import lombok.Data;

/**
 * @author nilesh
 *
 */
@Data
public class Auth0UserModel implements Serializable{

	private static final long serialVersionUID= 1930697838289305719L;
	
	private String client_id;
	private String email;
	private String password;
	private String connection;
	private String username;
	private String given_name;
}
