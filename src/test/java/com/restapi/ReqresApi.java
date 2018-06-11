package com.restapi;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.javascript.host.speech.SpeechSynthesisUtterance;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;

public class ReqresApi {
	/*
	 * Send a get request to https://reqres.in/api/users
	 * Including query param -> page=2
	 * Accept type is json
	 * Verify status code 200, verify response body
	 * 
	 */

	@Test
	public void getUserTest() {
		given().accept(ContentType.JSON).and().params("page", 2)
		.when().get(" https://reqres.in/api/users")
		.then().assertThat().statusCode(200);
		
		Response response=given().accept(ContentType.JSON)
				.and().params("page", 2)
				.when().get(" https://reqres.in/api/users");
		System.out.println(response.getStatusLine());
		System.out.println("===============");
		System.out.println(response.getContentType());
		System.out.println("===============");
		System.out.println(response.headers());
		System.out.println("===============");
		System.out.println(response.body().asString());
		

		//add assertions for parts of response.
		
		  assertEquals(200,response.getStatusCode());
		  assertTrue(response.getContentType().contains("application/json"));
		  
		  Header header=new Header("X-Powered-By", "Express");
		  assertTrue(response.getHeaders().asList().contains(header));
		  
		  JsonPath jsonPath=response.jsonPath();
		  assertEquals(12,jsonPath.getInt("total"));
		  assertEquals(4, jsonPath.getInt("total_pages"));
		  assertEquals(4, jsonPath.getInt("data.id[0]"));
			
			//Verify that Charles's id is 5
		  assertEquals(5, jsonPath.getInt("data.find{it.first_name=='Charles'}.id"));
          assertEquals("Eve", jsonPath.getString("data.find{it.id==4}.first_name"));
          assertEquals("Ramos", jsonPath.getString("data.find{it.id==6}.last_name"));
		  
		
		
		
		
		
		
		
		
		
		
	
	}

}
