package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*C - Create
 *R - Read/Retrieve
 *U - Update
 *D - Delete
 */

public class UserEndPoint {

	
//	##############CREATE-USER##############
	public static Response createUsers(User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_url);
		return response;
	}

//	##############READ-USER##############
	public static Response readUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when()
				.get(Routes.get_url);
		return response;
}

//	##############UPDATE-USER##############
	public static Response updateUser(String userName, User payload ) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username",userName)
				.when()
				.put(Routes.put_url);
		return response;
}

//	##############DELETE-USER##############
	public static Response deleteUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url);
		return response;
}
	
	
}
