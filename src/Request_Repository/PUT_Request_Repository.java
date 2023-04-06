package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_Method.Get_Data;

public class PUT_Request_Repository {

	public static String baseuri()
	{
		String baseuri ="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource ="/api/users/2";
		return resource; 
	}
	public static String PUT_REQUEST_TC1() throws IOException
	{
		ArrayList<String> Data = Get_Data.Get_Data_Excel("PUT Data", "tc1");
		//System.out.println(Data);
		String Name = Data.get(2);
		String Job = Data.get(3);
		String requestbody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestbody;

	}
} 
