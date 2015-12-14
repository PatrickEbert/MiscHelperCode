import java.io.*;
import java.util.*;

public class UnitTest
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to UnitTesting!");
		/*CLASS GEOLOCATION DISTANCE CALCULATION
		System.out.println("C L A S S  G E O L O C A T I O N  D I S T A N C E  C A L C U L A T I O N");
		System.out.println("Distanz Lachendorf (52.614549;10.23677) - Zurich (47.3844448;8.509779): " + Geolocation.calculateDistance(52.61454,10.23677,47.3844448,8.509779));
		System.out.println("Distanz Lachendorf (52.614549;10.23677) - Zurich (47.3844448;8.509779): " + Geolocation.calculateDistance(new Geolocation(52.61454,10.23677),new Geolocation(47.3844448,8.509779)));
		System.out.println("Distanz Lachendorf (52.614549;10.23677) - Zurich (47.3844448;8.509779): " + new Geolocation(52.61454,10.23677).getDistance(new Geolocation(47.3844448,8.509779)));
		*/
		//GOOGLE GEOCODING
		try
		{
		//https://maps.googleapis.com/maps/api/geocode/json?address=Fasanenweg+16+29331+Lachendorf
		//https://maps.googleapis.com/maps/api/geocode/json?address=Hohlstrasse+347+8004+Zurich
		System.out.println("\nG O O G L E G E O C O D I N G");
		//System.out.println("Geocode Adresse: Fasanenweg 16, 29331 Lachendorf:");
		//JSONObject json = new JSONObject(GeoCoder.geocode("google|JSON|","Fasanenweg 16, 29331 Lachendorf"));
		
		int counter = 0;
		BufferedReader br = new BufferedReader(new FileReader("TaxBaseGeoLocF2.csv"));
		String currentLine;
		PrintWriter pw = new PrintWriter("TaxBaseGeoLoc2.csv","UTF-8");
		pw.println("City;TaxBase;Latitude;Longitude");
		while((currentLine = br.readLine()) != null)
		{
			counter++;
			System.out.println(counter);
			String latitude = "";
			String longitude = "";
			String searchString = currentLine.split(";")[0] + ", UK";
			try
			{
			JSONObject json = new JSONObject(GeoCoder.geocode("google|JSON|",searchString));
			latitude = ((JSONObject)((JSONObject)((JSONObject)((JSONArray)json.get("results")).get(0)).get("geometry")).get("location")).get("lat").toString();
			longitude = ((JSONObject)((JSONObject)((JSONObject)((JSONArray)json.get("results")).get(0)).get("geometry")).get("location")).get("lng").toString();
			pw.println(currentLine + ";" + latitude + ";" + longitude);
			}catch(JSONException jEx)
			{
				pw.println(currentLine + ";0;0");
			}
			Random randGen = new Random();
			int randomNumber = randGen.nextInt(900);
			Thread.sleep(100 + randomNumber);
		}
		br.close();
		pw.close();
		/*
		String searchString = "Adur;
		JSONObject json = new JSONObject(GeoCoder.geocode("google|JSON|",searchString));
		String latitude = ((JSONObject)((JSONObject)((JSONObject)((JSONArray)json.get("results")).get(0)).get("geometry")).get("location")).get("lat").toString();
		String longitude = ((JSONObject)((JSONObject)((JSONObject)((JSONArray)json.get("results")).get(0)).get("geometry")).get("location")).get("lng").toString();
		System.out.println(json);
		*/
		
		//System.out.println("Geocode Adresse: Hohlstrasse 347, 8004 Zurich:");
		//System.out.println(GeoCoder.geocode("google|XML|","Hohlstrasse 347, 8004 Zurich"));
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		
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