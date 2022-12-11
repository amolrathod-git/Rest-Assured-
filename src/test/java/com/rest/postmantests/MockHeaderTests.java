package com.rest.postmantests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

import  static io.restassured.RestAssured.*;

import java.util.HashMap;



public class MockHeaderTests {
	
	RequestSpecification requestSpecification;
	
	@BeforeClass
	public void beforeClass() {
		HashMap<String, String> headers =new HashMap<String, String>();
		headers.put("requestheader", "value2");
		headers.put("x-mock-match-request-headers", "requestheader");
		
		 requestSpecification =with().
				baseUri("https://cb5d4d43-93de-4436-8d52-d755454d067b.mock.pstmn.io").
		        headers(headers).
		        log().all();
		
	}
	
	@Test
	public void multipleHeaders() {
		
		
		given().spec(requestSpecification).
		when().
			get("/get").
		then().
			log().all().
			assertThat().
			statusCode(503);
		
	}
	
	
	
	@Test
	public void validateResopnseHeaders() {
		given().spec(requestSpecification).
			
		when().
		   	get("/get").
		then().
			log().all().
			assertThat().
			statusCode(503).
			header("response-header","res-NO");
		
	}

}
