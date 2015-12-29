/**
 * This class supports websearch of telco directory data
 * Tel.search.ch API Developer Key: "bf5afd1bbbe3405e957334ac1d6f9a05"
 * 
 * @author Patrick Ebert 21-Dez-2015
 * 
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class TelDirectorySearch
{
	// Test Call
	// http://tel.search.ch/api/?wo=hohlstrasse+345+8004+8004+Zurich&key=bf5afd1bbbe3405e957334ac1d6f9a05
	public static String getSearchEntriesPerAdress(String adress)
	{
		String response = "";
		String query = "http://tel.search.ch/api/?wo=" + adress.trim().replace(" ","+") + "&key=bf5afd1bbbe3405e957334ac1d6f9a05";
		try
		{
			URL googleUrl = new URL(query);
			URLConnection googleUrlCon = googleUrl.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(googleUrlCon.getInputStream()));
			String line;
			while((line = br.readLine())!= null)
			{
				response += line;
			}
		}catch(Exception ex)
		{
			System.out.println("Exception in TelDirectorySearch.getSearchEntriesPerAdress: " + ex.toString());
		}
		return response;
	}
	
	public static void searchFile(String sourceFilename, String sourceCharset, String sourcePattern, String sourceDelimiter, String targetFilename, String targetPattern, String targetDelimiter)
	{
		Random rand = new Random();
		List<String> addresses = FileHelper.readFileToArray(sourceFilename,sourceCharset,true);
		List<String> entries = new ArrayList<String>();
		Iterator i = addresses.iterator();
		int x = 0;
		while(i.hasNext())
		{
			Misc.pause(250 + rand.nextInt(1000));
			String address = (String)i.next();
			entries.add(StringHelper.evaluatePattern(address,targetPattern,sourceDelimiter).replace("\"","") + targetDelimiter + TelDirectorySearch.getSearchEntriesPerAdress(StringHelper.evaluatePattern(address,sourcePattern,sourceDelimiter).replace("\"","")));
			System.out.println(++x + " of " + addresses.size());
		}
		FileHelper.writeArrayToFile(entries,targetFilename,false);
	}
}