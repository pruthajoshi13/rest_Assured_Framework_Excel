package Test_Class;

import java.io.IOException;
//import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_Method.Common_Method_API_GET;
import Common_Method.Common_Method_Utilities;
import Request_Repository.GET_Request_Repository;
import io.restassured.path.json.JsonPath;

public class GET_TC1 {
	@Test
	public static void orchestrator() throws IOException
	{    
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseUri = GET_Request_Repository.baseuri();
		String resource = GET_Request_Repository.resource();
		
		for(int i=0 ; i<5 ; i++) 
        {
		 responseStatuscode = Common_Method_API_GET.responsestatuscode_extractor(baseUri, resource);
		
		 if (responseStatuscode == 200)
		  {
			responseBody = Common_Method_API_GET.responsebody_extractor(baseUri, resource);
			responseBodyValidator(responseBody);
			
			break;
	      }
          else
          {
        	  System.out.println("correct status code is not found in the iteration " + i);
          }
        } 
		Common_Method_Utilities.evidanceFileCreator("GET_TC1", resource, responseBody);
				
		Assert.assertEquals(responseStatuscode, 200);
	}
   public static void responseBodyValidator(String responseBody)
	{
		// create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responseBody);

		// extract responsebody parameters
		int res_page= jsp.getInt("page");
		int res_per_page= jsp.getInt("per_page");
		int res_total = jsp.getInt("total");
		int res_total_pages = jsp.getInt("total_pages");
		
		int res_array_length = jsp.getInt("data.size()");
		System.out.println("The length of array is" +res_array_length);
		int d_id[]= {7,8,9,10,11,12};
		String d_email[]= {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"}; 
		String d_fname[]= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
		String d_lname[]= {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
		String d_avatar[]= {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
		
		System.out.println("page : " + res_page + "\nper_page : " + res_per_page + "\ntotal : " + res_total + "\ntotal_page : " + res_total_pages);
		
		System.out.println("The data array length is " +res_array_length);
		Assert.assertEquals(res_page, 2);
		Assert.assertEquals(res_per_page, 6);
		Assert.assertEquals(res_total, 12);
		Assert.assertEquals(res_total_pages, 2);

		for(int i=0; i<res_array_length; i++)
		{
			int id= d_id[i];//jsp.getInt("data["+i+"].id"); 
			String email = d_email[i]; //jsp.getString("data["+i+"].email");
			String first_name = d_fname[i]; //jsp.getString("data["+i+"].first_name");
			String last_name = d_lname[i]; //jsp.getString("data["+i+"].last_name");
			String avatar =d_avatar[i];//jsp.getString("data["+i+"].avatar");
			
			int res_id = jsp.getInt("data[" + i + "].id");
			String res_email = jsp.getString("data[" + i + "].email");
			String res_first_name = jsp.getString("data[" + i + "].first_name");
			String res_last_name = jsp.getString("data[" + i + "].last_name");
			String res_avatar = jsp.getString("data[" + i + "].avatar");

			
			System.out.println("Id : " + res_id + "\nEmail : " + res_email + "\nFirst Name : " + res_first_name + "\nLast_Name : " + res_last_name + "\nAvatar :"+ res_avatar);
		// validate responsebody parameter

			Assert.assertEquals(id,res_id);
			Assert.assertEquals(email,res_email);
			Assert.assertEquals(first_name,res_first_name);
			Assert.assertEquals(last_name,res_last_name);
			Assert.assertEquals(avatar,res_avatar);
			
		}
		System.out.println("Get data array inserted sucssefuly" + "\n " );
		
	/*	URL res_url= jsp
		Assert.assertEquals(res_url,"https://reqres.in/#support-heading");
		System.out.println("Get API validated Successfully");
		
		URL url=new URL("http://www.javatpoint.com/java-tutorial");  
  
System.out.println("Protocol: "+url.getProtocol());  
System.out.println("Host Name: "+url.getHost());  
System.out.println("Port Number: "+url.getPort());  
System.out.println("File Name: "+url.getFile());  */		
	}

}
