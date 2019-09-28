package com.Insane.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TwitterTest {
	String ConsumerKey = "0rpSgO72BnucMZkdZwjOgOlg3";
	String ConsumerSecret = "uZiJtiGnMKyUBBOGUDZZNUI5ug9edcCmAyJupsEB2twj1vcvHn";
	String Token = "104208652-kbzIeu656a18eYECiljHAxxjaFMRWfO2VjFBAiw0";
	String TokenSecret = "TqlRjVmcrNGdQ556OKFqPryYZl25DiZCUqJjCc1uTgpP9";
	String Tweetid, id1;
	RequestSpecification httpRequest;

	@Test(priority = 1, description = "Create a Tweet")
	public void CreateTweet() {
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		httpRequest = RestAssured.given();

		Response Resp = RestAssured.given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
				.queryParam("status", "Twitter API Testing").log().all().when().get("/show.json?id=1177635783542329344")
				.then().assertThat().statusCode(200).log().all().extract().response();

		// Response Resp = httpRequest.request(Method.GET,
		// "/show.json?id=1177635783542329344");
		String responseBody = Resp.getBody().asString();
		System.out.println("res;" + responseBody);
		/*
		 * String CreateTwe = Resp.asString(); JsonPath js = new JsonPath(CreateTwe);
		 * 
		 * System.out.println("id is" + js.get("id"));
		 * 
		 * Tweetid = (js.get("id")).toString();
		 * 
		 * System.out.println("Id of newly Created Tweet is \t" + Tweetid);
		 */

	}

}
