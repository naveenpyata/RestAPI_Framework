package api.testData;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.User_End_Points;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setupData() {
		
		Faker faker = new Faker();
		userpayload = new User();
		
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().fullName());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setUserStatus(0);

	}

		
	@Test(priority=1)
	public void test_PostUser() {

		Response response = User_End_Points.createUser(userpayload);			
		response.then().log().all();
		
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void test_GetUserByName() {
		
		Response response = User_End_Points.readUser(this.userpayload.getUsername());				
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void test_UpdateUserByName() {
			
	 //update data user payLoad
		
//		userpayload.setUsername(faker.name().fullName());
//		userpayload.setFirstName(faker.name().firstName());
//		userpayload.setLastName(faker.name().lastName());
		
		Response response = User_End_Points.updateUser(this.userpayload.getUsername(),userpayload);			
		response.then().log().all();
		
		 Assert.assertEquals(response.getStatusCode(), 200);
		 
     //Checking data after update
			
		 Response responseAfterUpdate = User_End_Points.readUser(this.userpayload.getUsername());
		 responseAfterUpdate .then().log().all(); 
		
		 Assert.assertEquals(response.getStatusCode(), 200);
		 		
	}
	
	@Test(priority=4)
	public void test_DeleteUserByName() {
		
		Response responseDelete = User_End_Points.deleteUser(this.userpayload.getUsername());	
		responseDelete.then().log().all();
		
		Assert.assertEquals(responseDelete.getStatusCode(), 200);	
	}
	
}
