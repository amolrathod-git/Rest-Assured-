package com.rest.postmantests;




import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import  io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 

public class ResponseSpecificationBuilder {
	
	
	
	ResponseSpecification responseSpecification;
	
	@BeforeClass
	public void beforeClass() {
		
		/*
		 * requestSpecification =with(). baseUri("https://api.getpostman.com").
		 * header("x-api-key",
		 * "PMAK-63858105d6a8db53e044edc6-7fd69e993c92a450ec486d93bf33c813f5").
		 * log().all();
		 */
		
		RequestSpecBuilder  requestSpecBuilder =new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("x-api-key", "PMAK-63858105d6a8db53e044edc6-7fd69e993c92a450ec486d93bf33c813f5");
		requestSpecBuilder.log(LogDetail.ALL);
		
		requestSpecification = requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).
				log(LogDetail.ALL);
		
		responseSpecification= responseSpecBuilder.build();
			
	}
	
	@Test
	public void validateResponseStatusCode() {
		
	
				get("/workspaces").
				then().spec(responseSpecification);
				
		
	}
	
	@Test
	public void validateResponseBody() {
		Response response=get("/workspaces").
				then().spec(responseSpecification).
				extract().
				response();
		
		assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));
	}
	
	


}

