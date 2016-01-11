import java.io.*;
import java.util.*;

public class UnitTest
{
	public static void main(String[] args)
	{
		// S T A N D A R D  S E T T I N G S
		System.out.println("Welcome to UnitTesting!");
		Random rand = new Random();
		
		// RUN BATCH API CALLS FOR BIG DATA SAMPLE DATA COLLECTING
		GeoCoder.geocodeFile("YaGeoSample.csv", "UTF-8", "[1], [3] [11]", ",", "yahoo|json|", "resYaGeoSample.csv", "[0];ZHOpenDataAdresses;YAHOO", ";");
		TelDirectorySearch.searchFile("TelSearchSample.csv","UTF-8","[1], [3] [11]",",","resTelSearchSample.csv","[0]",";");
		GeoCoder.geocodeFile("GooGeoSample.csv", "UTF-8", "[1], [3] [11]", ",", "google|json|", "resGooGeoSample.csv", "[0]", ";");
		
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