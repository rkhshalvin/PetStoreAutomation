package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 15));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("**********Creating User**********");
		
		Response response = UserEndPoint2.createUsers(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**********User Created**********");

	}
	
	@Test(priority=2)
	public void testGetUser() {

		logger.info("**********Reading User Info**********");
		
		Response response = UserEndPoint2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("**********User Info Displayed**********");

	}
	
	@Test(priority=3)
	public void updateUser() {
		
		logger.info("**********Updating User Info**********");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoint2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = UserEndPoint2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("**********User Info Updated**********");

	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response getResponse = UserEndPoint2.readUser(
			    this.userPayload.getUsername()
			);

			getResponse.then().log().all();

			System.out.println("GET Status Code: " + getResponse.getStatusCode());
		
		logger.info("**********Deleting User**********");
		
		Response response = UserEndPoint2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("**********User Deleted**********");

	}
	
/*@Test(priority=4)
	public void testDeleteUser() {

	    logger.info("**********Deleting User**********");

	    String username = this.userPayload.getUsername();

	    // Check whether user exists before deletion
	    Response getResponse = UserEndPoint2.readUser(username);

	    System.out.println("GET Before DELETE: "
	            + getResponse.getStatusCode());

	    // Perform DELETE
	    Response deleteResponse = UserEndPoint2.deleteUser(username);

	    // Print complete DELETE response
	    deleteResponse.then().log().all();

	    System.out.println("Username: " + username);
	    System.out.println("DELETE Status Code: "
	            + deleteResponse.getStatusCode());
	    System.out.println("DELETE Response Body: "
	            + deleteResponse.asPrettyString());

	    Assert.assertEquals(
	            deleteResponse.getStatusCode(),
	            200,
	            "DELETE request failed"
	    );

	    logger.info("**********User Deleted**********");
	}*/


}
