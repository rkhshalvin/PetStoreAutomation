package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.endpoints.PetEndPoints;
import api.endpoints.UserEndPoint;
import api.payloads.Pet;
import api.payloads.User;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class DDTests {

	public Logger logger = LogManager.getLogger(this.getClass());

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = dataProvider.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email,
			String Password, String Phone) {
		logger.info("**********Creating User**********");

		User userPayload = new User();

		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);

		Response response = UserEndPoint.createUsers(userPayload);

		logger.info("Name : " + userPayload.getFirstName() + " " + userPayload.getLastName());

		logger.info("Username : " + userPayload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User Created**********");

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = dataProvider.class)
	public void testDeleteUser(String uName) {

		logger.info("**********Deleting User**********");

		Response response = UserEndPoint.deleteUser(uName);

		logger.info("Username : " + uName);

		logger.info("**********User Deleted**********");

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3, dataProvider = "PetData", dataProviderClass = dataProvider.class)
	public void testPostPet(String petId, String catID, String catName, String petName, String phURLs, String tagID,
			String tagName, String status) {
		logger.info("**********Creating Pet Record**********");

		Pet petPayload = new Pet();

		petPayload.setId(Integer.parseInt(petId));
		Pet.Category cat = new Pet.Category();
		cat.setId(Integer.parseInt(catID));
		cat.setName(catName);
		petPayload.setCategory(cat);
		petPayload.setName(petName);
		petPayload.setPhotoUrls(Arrays.asList(phURLs));

		String[] tagIDArray = tagID.split(";");
		String[] tagNameArray = tagName.split(";");

		List<Pet.Tag> tagList = new ArrayList<>();

		for (int i = 0; i < tagIDArray.length; i++) {
			Pet.Tag tag = new Pet.Tag();
			tag.setId(Integer.parseInt(tagIDArray[i].trim()));
			tag.setName(tagNameArray[i].trim());

			tagList.add(tag);
		}

		petPayload.setTags(tagList);
		;
		petPayload.setStatus(status);

		Response response = PetEndPoints.createPet(petPayload);

		logger.info("Pet Name : " + petPayload.getName());
		logger.info("PetID : " + petPayload.getId());
		logger.info("Animal ID : " + petPayload.getCategory().getId() + " Animal Name : "
				+ petPayload.getCategory().getName());
		logger.info("Status : " + petPayload.getStatus());

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("**********Pet Record Created**********");
	}

	@Test(priority = 4, dataProvider = "PetIDs", dataProviderClass = dataProvider.class)
	public void testDeletePet(String petID) {
		
		logger.info("**********Deleting Pet Data**********");
		
		int id = Integer.parseInt(petID);
		
		Response getResponse = PetEndPoints.getPet(id);
		
		String petName = getResponse.jsonPath().getString("name");

		Response response = PetEndPoints.deletePet(id);
		
		 response.then().log().all();
		
		logger.info("Pet Name : " +petName);
		logger.info("Pet ID : "+id);
		
		logger.info("**********Pet Data Deleted**********");
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
