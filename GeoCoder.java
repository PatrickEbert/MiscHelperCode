import java.lang.*;
import java.net.*;
import java.io.*;

/**
* This is a Java based geocoding tool.
* it's purpose is to be used as commandline interface tool to geocode data
* and it could be integrated in java applications using the the static functions as documented
*
* @author: Patrick Ebert
* 
*/

public class GeoCoder
{
	/***
	* geocode gets provider details to query the right geocoding provider and returns the http response from the specific server
	*
	* @param provider it is '|' delimited multivalue input parameter valid inputs are:
	*		"GOOGLE|{JSON;XML}|KEY(optional)" note: if no key is given do not forget the last '|' and if no key is provided google allows only 3000 single geocode requests per day
	*
	* @param address this is just the string which is send to the webservers address parameter. note: ' ' are replaced with '+'
	*
	* @return the return value of this function is simply the http response from the webserver in text. If there is further usage as JSON or XML object it must be recastet into one
	*/
	public static String geocode(String provider, String address) throws Exception
	{
		if(provider.indexOf("|") >= 0)
		{
			String webService = "";			
			webService = provider.substring(0,provider.indexOf("|"));
			
			switch(webService.toLowerCase())
			{
					case "google":
					{
						try
						{
						// a sample google maps geocoding call is:
						// https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA
						String responseType = provider.substring(webService.length() + 1,provider.indexOf("|",webService.length() + 1));
						switch(responseType.toLowerCase())
						{
							case "json":
								responseType = "json";									break;
							case "xml":
								responseType = "xml";
								break;
							default:
									throw new Exception("no valid responseType for google is provided. Valid responsetypes are JSON and XML.");
						}
						String key = provider.substring(webService.length() + responseType.length() + 2);
						String query = "https://maps.googleapis.com/maps/api/geocode/" + responseType + "?address=" + address.trim().replace(" ","+");
						if(!key.trim().equals(""))
						{
							query += "?key=" + key;
						}
						System.out.println(query);
						URL googleUrl = new URL(query);
						URLConnection googleUrlCon = googleUrl.openConnection();
						BufferedReader br = new BufferedReader(new InputStreamReader(googleUrlCon.getInputStream()));
						String line;
						String response = "";
						while((line = br.readLine())!= null)
						{
							response += line;
						}
						return response;
						}catch(IndexOutOfBoundsException ioobEx)
						{
							throw new Exception("Please provide valid provider information for google: GOOGLE|{JSON;XML}|YourKey(optional)");
						}
					}
					case "yahoo":
					{
						return "this is yahoo";
					}
					default:
					{
						throw new Exception("no valid webservices is provided in section one of parameter provider. Allowed webservices are: \"google\",\"yahoo\"");
					}
			}
		}
		else
		{
			throw new Exception("Not a valid provider String. Please delimit parameters within that parameter with '|'");
		}
	}
}