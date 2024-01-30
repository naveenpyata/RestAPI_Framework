package api.testData;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.User_End_Points;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph) {

     User userPayload=new User();
     
     userPayload.setId(Integer.parseInt(userID));
     userPayload.setUsername(userName);
     userPayload.setFirstName(fname);
     userPayload.setLastName(lname);
     userPayload.setEmail(useremail);
     userPayload.setPassword(pwd);
     userPayload.setPhone(ph);
     
     
     Response response = User_End_Points.createUser(userPayload);    
     Assert.assertEquals(response.getStatusCode(), 200);
}
	
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void deleteUser(String userName) {

    Response response = User_End_Points.deleteUser(userName);
    Assert.assertEquals(response.getStatusCode(), 200);
}
	
	
	
}