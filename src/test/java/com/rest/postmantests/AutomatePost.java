package com.rest.postmantests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import  static io.restassured.RestAssured.*;

public class AutomatePost {
	
	//RequestSpecification reqeuestSpecification;
	//ResponseSpecification responseSpecification;
	
	@BeforeClass
	public void beforeClass() {
		
		RequestSpecBuilder requestSpecBuilder =new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("x-api-key", "PMAK-63858105d6a8db53e044edc6-7fd69e993c92a450ec486d93bf33c813f5");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification= requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder =new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200);
		responseSpecBuilder.expectContentType(ContentType.JSON);
		responseSpecBuilder.log(LogDetail.ALL);
		RestAssured.responseSpecification=responseSpecBuilder.build();
		
		}
	
	String payload= "{\r\n"
			+ "    \"workspace\": \r\n"
			+ "        {\r\n"
			+ "            \"name\": \"My Workspace_03\",\r\n"
			+ "            \"type\": \"personal\",\r\n"
			+ "            \"description\": \"personal\"\r\n"
			+ "        }\r\n"
			+ "}";
	
	String wokrspaceId="0981e666-bded-4783-94ec-1e63c732e78f";
	
	   @Test
	   public void validatePost() {
		given().
			body(payload).
		
		when().
		 	post("/workspaces").
		then().
			
			assertThat().
			body("workspace.name", equalTo("My Workspace_03"),
					"workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
       
		 
	}
	   
	   @Test
	   public void validateDelete() {
		   
		   given().
		    	pathParam("workspaceId", wokrspaceId).
		   when().
		   		delete("/workspaces/{workspaceId}").
		   then().
		   		log().all().
		   		assertThat().
		   		body(
		   				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
		   				"workspace.id", equalTo(wokrspaceId));
		   
	   }

}
