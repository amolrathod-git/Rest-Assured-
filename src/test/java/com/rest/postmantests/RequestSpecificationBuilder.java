package com.rest.postmantests;




import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import  io.restassured.specification.RequestSpecification;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 

public class RequestSpecificationBuilder {
	
	
	RequestSpecification requestSpecification;
	
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
	}
	
	@Test
	public void validateResponseStatusCode() {
		
		Response response=given().spec(requestSpecification).
				header("dummyHeaderKey","dummyHeaderValue").
				get("/workspaces").then().log().all().extract().response();
		assertThat(response.statusCode(), is(equalTo(200)));
		
	}
	
	@Test
	public void validateResponseBody() {
		Response response=given().spec(requestSpecification).get("/workspaces").then().log().all().extract().response();
		assertThat(response.path("workspaces[1].name").toString(), equalTo("My Workspace"));
	}
	
	


}

