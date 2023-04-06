package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_Method.Get_Data;

public class GET_Request_Repository {

	public static String baseuri()
	{
		String baseuri ="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource ="/api/users?page=2";
		return resource;
	}
	public static String GET_REQUEST_TC1() throws IOException
	{
		ArrayList<String> Data= Get_Data.Get_Data_Excel("POST Data","tc1");
	//System.out.println(Data);
	
		String requestbody="";
		return requestbody;

	}
}

