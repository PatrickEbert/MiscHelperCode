import java.io.*;
import java.util.*;

public class UnitTest
{
	public static void main(String[] args)
	{
		String filename = "SampleE2.csv";
		String charset = "UTF-8";
		System.out.println("Welcome to UnitTesting!");
		List<String>addresses = FileHelper.readFileToArray(filename,charset,true);
		List<String>geocodes = new ArrayList<String>();
		Iterator i = addresses.iterator();
		Random rand = new Random();
		while(i.hasNext())
		{
			try
			{
				String query = (String)i.next();
				System.out.println(StringHelper.evaluatePattern(query,"[1], [3] [11]",",").replace("\"",""));
				JSONObject json = new JSONObject(GeoCoder.geocode("google|json|",StringHelper.evaluatePattern(query,"[1], [3] [11]",",").replace("\"","")));
				System.out.println(json);
				geocodes.add(StringHelper.evaluatePattern(query,"[0]",",").replace("\"","") + ";" + json);
				Thread.sleep(1000 + rand.nextInt(1000));
			}catch(Exception ex){System.out.println("UnitTest.main Exception: " + ex.toString());}
		}
		FileHelper.writeArrayToFile(geocodes,"resSample1.csv",false);
		
		/*CLASS GEOLOCATION DISTANCE CALCULATION
		System.out.println("C L A S S  G E O L O C A T I O N  D I S T A N C E  C A L C U L A T I O N");
		System.out.println("Distanz Lachendorf (52.614549;10.23677) - Zurich (47.3844448;8.509779): " + Geolocation.calculateDistance(52.61454,10.23677,47.3844448,8.509779));
		System.out.println("Distanz Lachendorf (52.614549;10.23677) - Zurich (47.3844448;8.509779): " + Geolocation.calculateDistance(new Geolocation(52.61454,10.23677),new Geolocation(47.3844448,8.509779)));
		System.out.println("Distanz Lachendorf (52.614549;10.23677) - Zurich (47.3844448;8.509779): " + new Geolocation(52.61454,10.23677).getDistance(new Geolocation(47.3844448,8.509779)));
		*/
		/*GOOGLE GEOCODING
		//https://maps.googleapis.com/maps/api/geocode/json?address=Fasanenweg+16+29331+Lachendorf
		//https://maps.googleapis.com/maps/api/geocode/json?address=Hohlstrasse+347+8004+Zurich
		System.out.println("Geocode Adresse: Fasanenweg 16, 29331 Lachendorf:");
		try
		{
		JSONObject json = new JSONObject(GeoCoder.geocode("google|JSON|","Fasanenweg 16, 29331 Lachendorf"));
		}catch(Exception ex){}
		*/
		
		/*GOOGLE GEOCODE XML
		//System.out.println("Geocode Adresse: Hohlstrasse 347, 8004 Zurich:");
		//System.out.println(GeoCoder.geocode("google|XML|","Hohlstrasse 347, 8004 Zurich"));
		*/
	
		/*LOGGING
		System.out.println("\nN O W  L O G G I N G");
		Misc.log("Hallo Welt!");
		Misc.log("Hallo Welt!","LogBook.lg");
		*/
		/*WGS84 (LONGITUDE AND LATITUDE) CONVERTED TO CH1903 (SWISS MILITARY COORDINATES)
		Geolocation g1 = new Geolocation(8.5097799, 47.3844448);
		System.out.println("Point (lat 47.3844448, lng 8.5097799): " + g1.getCH1903XValue() + ":" + g1.getCH1903YValue());
		Geolocation g2 = new Geolocation(7.029184, 47.223611);
		System.out.println("Point (lat 47.223611, lng 7.029184): " + g2.getCH1903XValue() + ":" + g2.getCH1903YValue());
		*/
		
	}
}