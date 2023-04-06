package Test_Class;
import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common_Method.Common_Method_API_PATCH;
import Request_Repository.PATCH_Request_Repository;
import io.restassured.path.json.JsonPath;

public class PATCH_TC1 {
	@Test
	public static void orchestrator() throws IOException
	{    
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseUri = PATCH_Request_Repository.baseuri();
		String resource = PATCH_Request_Repository.resource();
		String requestBody = PATCH_Request_Repository.PATCH_REQUEST_TC1();
		for(int i=0 ; i<5 ; i++) 
        {
		 responseStatuscode = Common_Method_API_PATCH.responsestatuscode_extractor(baseUri, resource, requestBody);
		 if (responseStatuscode == 200)
		  {
			responseBody = Common_Method_API_PATCH.responsebody_extractor(baseUri, resource, requestBody);
			responseBodyValidator(responseBody);
			
			break;
	      }
          else
          {
        	  System.out.println("correct status code is not found in the iteration " + i);
          }
        } 
		Common_Method.Common_Method_Utilities.evidanceFileCreator("PATCH_TC1",requestBody,responseBody);
		
		Assert.assertEquals(responseStatuscode, 200);
		
     }
 
    public static void responseBodyValidator(String responseBody)
	{
		// create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responseBody);

		// extract responsebody parameters
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_updatedAt = jsp.getString("updatedAt");

		System.out.println("name : " + res_name + "\njob : " + res_job );

		// validate responsebody parameter
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		
		// extract date from updatedAt parameter
		String actual_date = res_updatedAt.substring(0, 10);
		String current_date = LocalDate.now().toString(); // Create a date object
		Assert.assertEquals(actual_date, current_date);
		System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);

	}
}
