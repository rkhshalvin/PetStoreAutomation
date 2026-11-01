package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*C - Create
 *R - Read/Retrieve
 *U - Update
 *D - Delete
 */

public class UserEndPoint2 {
	
	static ResourceBundle getURL()
	{		
	ResourceBundle routes = ResourceBundle.getBundle("routes");
	return routes;			
	}		
	
//	##############CREATE-USER##############
	public static Response createUsers(User payload) {
		
		String post_url = getURL().getString("post_url");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		return response;
	}

//	##############READ-USER##############
	public static Response readUser(String userName) {
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
				.pathParam("username", userName)
				.when()
				.get(get_url);
		return response;
}

//	##############UPDATE-USER##############
	public static Response updateUser(String userName, User payload ) {
		
		String put_url = getURL().getString("put_url");

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username",userName)
				.when()
				.put(put_url);
		return response;
}

//	##############DELETE-USER##############
	public static Response deleteUser(String userName) {
		
		String delete_url = getURL().getString("delete_url");

		Response response = given()
				.pathParam("username", userName)
				.when()
				.delete(delete_url);
		return response;
}
	
	
}
