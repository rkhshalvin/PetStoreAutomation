package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*C - Create
 *R - Read/Retrieve
 *U - Update
 *D - Delete
 */

public class PetEndPoints {

//	##############CREATE-PET##############
	public static Response createPet(Pet payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(PetRoutes.post_url);

		return response;
	}

//	##############READ-PET##############
	public static Response getPet(int pet_id) {
		Response response = given()
				.pathParam("petId", pet_id)
				.when()
				.get(PetRoutes.get_url);
		return response;
	}

//	##############UPDATE-PET##############
	public static Response updatePet(Pet payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(PetRoutes.put_url);

		return response;
	}

//	##############DELETE-USER##############
	public static Response deletePet(int pet_id) {
		Response response = given()
				.pathParam("petId", pet_id)
				.when()
				.delete(PetRoutes.delete_url);
		return response;
	}

}
