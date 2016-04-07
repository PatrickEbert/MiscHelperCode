import java.io.*;
import java.net.*;

public class IOHelper
{
	public static String getWebResponseFromUrl(String url)
	{
		String response = "";
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream()));
			String line;
			while((line = br.readLine()) != null)
			{
				response += line;
			}
		}catch(Exception ex)
		{
			System.out.println("IOHelper.getWebResponseFromUrl: " + ex.getMessage());
		}
		return response;
	}
}