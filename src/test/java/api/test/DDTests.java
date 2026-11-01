package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payloads.User;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1, dataProvider = "Data", dataProviderClass= dataProvider.class)
	public void testPostUser(String UserID,String UserName,String FirstName, String LastName, String Email, String Password, String Phone) 
	{
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response = UserEndPoint.createUsers(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=1, dataProvider = "UserNames", dataProviderClass= dataProvider.class)
	public void testDeleteUser(String uName) {
		
		Response response = UserEndPoint.deleteUser(uName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
