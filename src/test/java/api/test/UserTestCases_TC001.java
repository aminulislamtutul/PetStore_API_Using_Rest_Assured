package api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UserTestCases_TC001 {
	Faker faker;
	User userPayload;
	
	public Logger logger; 
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		logger= LogManager.getLogger(this.getClass());
		logger.debug("debugging.....");
		
	}
	
	@Test(priority=1)
	public void testPostUser(){
		logger.info("********** Creating user  ***************");
		Response response = userEndPoints.createUser(userPayload);
				response.then().log().all();
				
				Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User is creatged  ***************");
		
	}
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("********** Reading User Info ***************");
		Response response = userEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User info  is displayed ***************");
		
	}
	@Test(priority=3)
	public void testUpdateUserByName() {
		logger.info("********** Updating User ***************");
		//Update data using Payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** User updated ***************");
		//Check data after update
		Response responseAfterupdate = userEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
				
		
	}
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("**********   Deleting User  ***************");
		Response response = userEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User deleted ***************");
		
		
	}

}
