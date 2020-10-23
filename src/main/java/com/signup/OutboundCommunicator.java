/**
 * 
 */
package com.signup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;



/**
 * @author nilesh
 *
 */
@Service
@Slf4j
public class OutboundCommunicator {

	@Value("${client_id}")
	private String clientId;
	
	@Value("${connection}")
	private String connection;
	
	public Auth0Response registerUser(UserRegBean user) throws JsonProcessingException {
		
		RestTemplate rt=new RestTemplate();
		ObjectMapper mapper=new ObjectMapper();
		Auth0UserModel userModel=convertToModel(user);
		String userModelJson=new ObjectMapper().writeValueAsString(userModel);
		log.info(userModelJson);
		
		HttpHeaders header=new HttpHeaders();
		header.set("Content-Type","application/json");
		HttpEntity<String> request=new HttpEntity<>(userModelJson,header);
		String authoResp=rt.postForObject("https://rajakumar.us.auth0.com/dbconnections/signup", request, String.class);	
		log.info(authoResp);
		Auth0Response auth0Resp= mapper.readValue(authoResp,Auth0Response.class);

		return auth0Resp;
	}

	private Auth0UserModel convertToModel(UserRegBean user) {

		Auth0UserModel userModel=new Auth0UserModel();
		userModel.setClient_id(clientId);
		userModel.setConnection(connection);
		userModel.setEmail(user.getEmail());
		userModel.setPassword(user.getPassword());
		userModel.setGiven_name(user.getFname()+" "+user.getLname());
		userModel.setUsername(user.getUserId());
		
		return userModel;
	}
}
