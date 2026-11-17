package api.test;

import java.util.Arrays;
import java.util.HashMap;
//import java.util.Random;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payloads.Pet;
import api.payloads.Pet.Tag;
import io.restassured.response.Response;

public class PetTests {

	Faker faker;
	Pet petPayload;
	public Logger logger;

	@BeforeClass
	public void setupData() {

		/*
		 * String[] statuses = {"available", "pending", "sold"}; Random random = new
		 * Random();
		 */
		faker = new Faker();
		petPayload = new Pet();

		petPayload.setId(faker.number().numberBetween(0, 99999));
		petPayload.setName(faker.funnyName().name());
		petPayload.setPhotoUrls(Arrays.asList(petPayload.getName() + "@url"));
		petPayload.setStatus(faker.options().option("available", "sold", "pending"));

		Map<Integer, String> categories = new HashMap<>();
		categories.put(1, "Dog");
		categories.put(2, "Cat");
		categories.put(3, "Bird");
		categories.put(4, "Turtle");
		categories.put(5, "Fish");
		categories.put(6, "Snake");
		categories.put(7, "Spider");
		int categoryId = faker.number().numberBetween(1, 7);
		Pet.Category category = new Pet.Category();
		category.setId(categoryId);
		category.setName(categories.get(categoryId));
		petPayload.setCategory(category);

		Map<Integer, String> tags = new HashMap<>();
		tags.put(1, "Friendly");
		tags.put(2, "Moody");
		tags.put(3, "Aggressive");
		tags.put(4, "Active");
		tags.put(5, "Curious");
		tags.put(6, "Excited");

		int tagId = faker.number().numberBetween(1, 6);
		Pet.Tag tag = new Tag();
		tag.setId(tagId);
		tag.setName(tags.get(tagId));
		petPayload.setTags(Arrays.asList(tag));
		
		
		logger = LogManager.getLogger(this.getClass());

	}
	@Test(priority = 1)
	public void testPostPet() {
		
		logger.info("**********Creating Pet**********");
		
		Response response = PetEndPoints.createPet(petPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**********Pet Created**********");

	}
	
	@Test(priority = 2)
	public void testGetPet() {
		
		logger.info("**********Getting Pet Info**********");
		
		Response response = PetEndPoints.getPet(this.petPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**********Pet Info Displayed**********");

	}
	
	@Test(priority = 3)
	public void testUpdatePet() {
		
		logger.info("**********Updating Pet**********");
		
		logger.info("Pet ID was : "+petPayload.getId()+", bearing Name :" +petPayload.getName());
		
		Response response = PetEndPoints.updatePet(petPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200,"Update Failed");
		
		logger.info("Update Successful");
		
		Response responseAfterUpdate = PetEndPoints.getPet(this.petPayload.getId());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("Current Pet ID is : "+this.petPayload.getId()+", bearing Name :" +this.petPayload.getName());

		
		logger.info("**********Pet Updated**********");

	}
	
	@Test(priority = 4)
	public void testDeletePet() {
		
		logger.info("**********Deleting Pet Info**********");
		
		Response response = PetEndPoints.deletePet(this.petPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**********Pet Info Deleted**********");

	}
	

}
