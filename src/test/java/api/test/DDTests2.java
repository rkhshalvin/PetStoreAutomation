package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import api.endpoints.UserEndPoint2;
import api.payloads.User;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class DDTests2 {
	
	public Logger logger = LogManager.getLogger(this.getClass());
	 
	@Test(priority=1, dataProvider = "Data", dataProviderClass= dataProvider.class)
	public void testPostUser(String UserID,String UserName,String FirstName, String LastName, String Email, String Password, String Phone) 
	{
		logger.info("**********Creating User**********");

		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response = UserEndPoint2.createUsers(userPayload);
		
		logger.info("Name : " + userPayload.getFirstName()+" "+userPayload.getLastName());

		logger.info("Username : " + userPayload.getUsername());
		
		 response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User Created**********");

	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass= dataProvider.class)
	public void testDeleteUser(String uName) {
		
		logger.info("**********Deleting User**********");
		
		Response response = UserEndPoint2.deleteUser(uName);
		
		 response.then().log().all();
		
		logger.info("Username : " +uName);
		
		logger.info("**********User Deleted**********");
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
