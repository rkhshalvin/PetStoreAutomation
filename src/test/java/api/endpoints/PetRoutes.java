package api.endpoints;

/*
 * ###########PET MODULE###########
 *Swagger Base URL : https://petstore.swagger.io/v2;
 * POST : https://petstore.swagger.io/v2/pet;
 * GET : https://petstore.swagger.io/v2/pet/{petId};
 * PUT : https://petstore.swagger.io/v2/pet;
 * DELETE : https://petstore.swagger.io/v2/pet/{petId};
 * 
 * 
 */


public class PetRoutes {

	public static String base_url = "https://petstore.swagger.io/v2";
	
	//###########PET MODULE###########

	public static String post_url = base_url+"/pet";
	public static String get_url = base_url+"/pet/{petId}";
	public static String put_url = base_url+"/pet";
	public static String delete_url = base_url+"/pet/{petId}";


}
