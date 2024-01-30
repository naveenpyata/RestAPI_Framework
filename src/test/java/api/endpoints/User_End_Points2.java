package api.endpoints;


import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User_End_Points2 {
	
	
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		
		return routes;
	}
	
	
	//Created for perform Create,Read,Update and Delete  Requests for the APIs
		
	public static Response createUser(User payload){
		
		String post_url = getURL().getString("post_url");
	    
		Response response=given()
		               .contentType(ContentType.JSON)
		               .accept(ContentType.JSON)
                       .body(payload)
                     .when()
                      .post(Routes.post_url);
		
    	return response;
	}
	
	public static Response readUser(String userName){
		
	String post_url = getURL().getString("get_url");	
		
	Response response=given()
			           .pathParam("userName",userName)

                     .when()
                      .get(Routes.get_url);
		
    	return response;
	}
	
	public static Response updateUser(String userName, User payload){
		
	Response response=given()
		               .contentType(ContentType.JSON)
		               .accept(ContentType.JSON)
                       .pathParam("userName",userName)
                       .body(payload)
                     .when()
                      .put(Routes.update_url);
		
    	return response;
	}
	
	public static Response deleteUser(String userName){
		
	Response response=given()
		        
                     .pathParam("username",userName)
                     .when()
                      .delete(Routes.delete_url);
		
    	return response;
	}
	
}