package Test_Class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_Method.Common_Method_API_POST;
import Request_Repository.POST_Request_Repository;
import io.restassured.path.json.JsonPath;

public class POST_TC1 {

@Test
	public static void orchestrator() throws IOException
	{   
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseUri = POST_Request_Repository.baseuri();
		String resource =POST_Request_Repository.resource();
		String requestBody = POST_Request_Repository.POST_REQUEST_TC1();
		for(int i=0 ; i<5 ; i++) 
        {
		 responseStatuscode = Common_Method_API_POST.responsestatuscode_extractor(baseUri, resource, requestBody);
				 
		if (responseStatuscode == 201)
		  {
			responseBody = Common_Method_API_POST.responsebody_extractor(baseUri, resource, requestBody);
				responseBodyValidator(responseBody);
					
			break;
	      }
          else
          {
        	  System.out.println("correct status code is not found in the iteration " + i);
          }
        } 
		Common_Method.Common_Method_Utilities.evidanceFileCreator("POST_TC1",requestBody,responseBody);
		
		Assert.assertEquals(responseStatuscode, 201);
		
     }

    public static void responseBodyValidator(String responseBody)
	{
		// create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responseBody);

		// extract responsebody parameters
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");

		//System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);

		// validate responsebody parameter
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotNull(res_id, "assertion error , id parameter is null");

		// extract date from createdAt parameter 
		String actual_date = res_createdAt.substring(0, 10);
		String current_date = 
		LocalDate.now().toString();// Create a date object
		Assert.assertEquals(actual_date, current_date);
		//System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);

	}
}

