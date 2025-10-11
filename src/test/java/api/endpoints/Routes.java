package api.endpoints;

public class Routes {
	//Base URL
	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	//User model
	public static String postUrl = baseUrl+"/user";
	public static String getUrl = baseUrl+"/user/{username}";
	public static String updateUrl = baseUrl+"/user/{username}";
	public static String deleteUrl = baseUrl+"/user/{username}";
	

}
