package com.globus.demo;

import com.globus.demo.model.User;
import com.globus.demo.token.Token;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

@SpringBootTest
class UserControllerTests {

	@Test
	void registrationUser() {
		User user = new User();
		user.setEmail("registrationUserTestEmail");
		user.setName("registrationUserTestName");
		user.setPassword("pass");
		given().
				contentType(ContentType.JSON).
				body(user).
		when().
				post("/registration").
		then().assertThat().
			statusCode(201).
			contentType(ContentType.JSON).
			body("status", equalTo(true)).
			body("objectToResponse.token", matchesPattern("\\d+registrationUserTestNamenull"));
	}

	@Test
	void doubleRegistrationUser() {
		User user = new User();
		user.setEmail("doubleRegistrationUserTestEmail");
		user.setName("doubleRegistrationUserTestName");
		user.setPassword("pass");
		given().
				contentType(ContentType.JSON).
				body(user).
		when().
				post("/registration").
		then().assertThat().
				statusCode(201).
				contentType(ContentType.JSON).
				body("status", equalTo(true)).
				body("objectToResponse.token", matchesPattern("\\d+doubleRegistrationUserTestNamenull"));

		given().
				contentType(ContentType.JSON)
				.body(user)
		.when()
				.post("/registration")
		.then().assertThat()
				.statusCode(400)
				.contentType(ContentType.JSON)
				.body("status", equalTo(false))
				.body("objectToResponse.token", equalTo(null));
	}

	@Test
	void auntificationUser() {
		User user = new User();
		user.setEmail("auntificationUserTestEmail");
		user.setName("auntificationUserTestName");
		user.setPassword("pass");
		JsonPath jsonPath1 = given().
				contentType(ContentType.JSON)
				.body(user)
		.when()
				.post("/registration")
		.jsonPath();

		String token = jsonPath1.getString("objectToResponse.token");

		given().
				contentType(ContentType.JSON)
				.body(user)
		.when()
				.post("/getuser")
		.then().assertThat()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body("status", equalTo(true))
				.body("objectToResponse.token", equalTo(token));
	}

}
