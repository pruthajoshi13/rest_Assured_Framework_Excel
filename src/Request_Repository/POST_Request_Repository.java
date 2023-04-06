package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_Method.Get_Data;

public class POST_Request_Repository {
	public static String baseuri()
	{
		String baseuri ="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource ="api/users";
		return resource;
	}
	
	public static String POST_REQUEST_TC1() throws IOException
	{
		ArrayList<String> Data= Get_Data.Get_Data_Excel("POST Data","tc1");
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
