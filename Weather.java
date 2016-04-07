import java.io.*;
import java.net.*;
import java.util.*;

/***
 * This class supports websearch of weather data
 * openWeather API Developer Key: "839aa7c01eff9bbdc15784ee9ee2530e"
 *
 * @author Patrick Ebert 6-Apr-2016
 *
 */

public class Weather
{
    public static void getWeatherFile(String sourceFilename, String targetFilename, boolean append)
    {
        List<String>cities = FileHelper.readFileToArray(sourceFilename,"UTF-8",false);
        List<String>weather = new ArrayList<String>();

        Iterator i = cities.iterator();
        Random rand = new Random();
        int x = 0;
        while(i.hasNext())
        {
            String city = (String)i.next();
            try
            {
                weather.add(city + ";" + Misc.now() + ";" + getWeather(city));
            }catch(Exception ex){System.out.println("Exception Weather.getWeatherFile " + ex.toString());}
            System.out.println(++x + " of " + cities.size() + " now querying " + city);
        }
        FileHelper.writeArrayToFile(weather,targetFilename,append);
    }

    /***
     * getWeather aquires weatherdata from several webServices, the defaultWebService is OpenWeather.org
     *
     * @param city - Location by Name
     * @return returns weather data for the given location
     */
    public static String getWeather(String city)
    {
        return getWeather("OpenWeather",city);
    }

    /***
     * getWeather aquires weatherdata from several webServices, the defaultWebService is OpenWeather.org
     *
     * @param webService - webservices, default openWeather.org
     * @param city - location by name
     * @return returns weather data for the given location
     */
    public static String getWeather(String webService, String city)
    {
        switch(webService.toLowerCase())
        {
            // London Call:
            // http://api.openweathermap.org/data/2.5/forecast/weather?q=London&APPID=839aa7c01eff9bbdc15784ee9ee2530e
            case "openweather": {
                String query = "http://api.openweathermap.org/data/2.5/forecast/forecast?q=" + city.replace(" ","+") + "&APPID=839aa7c01eff9bbdc15784ee9ee2530e";
                String response = "";
                try {
                    URL openWeatherUrl = new URL(query);
                    URLConnection openWeatherUrlCon = openWeatherUrl.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(openWeatherUrlCon.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } catch (Exception ex) {
                    System.out.println("Exception in UnitTest.openWeatherAPI: " + ex.toString());
                }
                return response;
            }
            default:
            {
                return "Weather.getWeather(String webService, String city): no valid webSeribce is provided. Valid responsetypes are OpenWeather.";
            }
        }
    }

    /***
     *
     * @param location location by Geolocation (WGS84)
     * @return returns weather data for the given location
     */
    public static String getWeather(Geolocation location)
    {
        return getWeather("openweather",location);
    }

    /***
     *
     * @param webService - webservices, default openWeather.org
     * @param location - location by Geolocation (WGS84)
     * @return returns weather data for the given location
     */
    public static String getWeather(String webService, Geolocation location)
    {
        switch(webService.toLowerCase())
        {
            case "openweather":
            {
                String query = "http://api.openweathermap.org/data/2.5/forecast/weather?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&APPID=839aa7c01eff9bbdc15784ee9ee2530e";
                String response = "";
                try {
                    URL openWeatherUrl = new URL(query);
                    URLConnection openWeatherUrlCon = openWeatherUrl.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(openWeatherUrlCon.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } catch (Exception ex) {
                    System.out.println("Exception in UnitTest.openWeatherAPI: " + ex.toString());
                }
                return response;
            }
            default:
            {
                return "Weather.getWeather(String webService, String city): no valid webSeribce is provided. Valid responsetypes are OpenWeather.";
            }
        }
    }
}