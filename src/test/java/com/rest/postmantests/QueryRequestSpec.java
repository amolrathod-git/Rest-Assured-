package com.rest.postmantests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class QueryRequestSpec {
	
	@BeforeClass
	public void beforeClass() {
		
		RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("x-api-key", "PMAK-63858105d6a8db53e044edc6-7fd69e993c92a450ec486d93bf33c813f5");
		requestSpecBuilder.log(LogDetail.ALL);
		
		RestAssured.requestSpecification=requestSpecBuilder.build();
	}
	
	@Test
	public void queryTest() {
		
		QueryableRequestSpecification  queriableRequestSpecification= SpecificationQuerier.
														query(RestAssured.requestSpecification);
		
		System.out.println("BaseURI is "+ queriableRequestSpecification.getBaseUri());
		System.out.println("Header(s) is/are "+ queriableRequestSpecification.getHeaders());
	}

}
