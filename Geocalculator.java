/**
* Geocalculator - 
*
* Purpose of this class is to provide a set of methods for several geospatial calculations
*
* @author Patrick Ebert
*
*/
public class Geocalculator
{
	/**
	* calculateDistance
	*
	* calculates the Distance between to points on the earth using the haversine formula.
	*
	* @param longitudeOrigin the longitude value for the origin
	*
	* @param latitudeOrigin the longitude value for the origin
	*
	* @param longitudeDestination the longitude value for the destination
	*
	* @param latitudeDestination the longitude value for the destination
	*
	* @return distance in kilometre
	*/
	public static double calculateDistance(double longitudeOrigin, double latitudeOrigin, double longitudeDestination, double latitudeDestination)
	{
		int R = 6371; // Earth radius
		double latDist = toRad(latitudeDestination - latitudeOrigin);
		double lngDist = toRad(longitudeDestination - longitudeOrigin);
		double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
					+ Math.cos(toRad(latitudeOrigin)) * Math.cos(toRad(latitudeDestination))
					* Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = c * R;
		return distance;
	}
	
	public static double calculateDistance(Geolocation origin, Geolocation destination)
	{
		int R = 6371; // Earth radius
		double latDist = toRad(destination.getLatitude() - origin.getLatitude());
		double lngDist = toRad(destination.getLongitude() - origin.getLongitude());
		double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
					+ Math.cos(toRad(origin.getLatitude())) * Math.cos(toRad(destination.getLatitude()))
					* Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = c * R;
		return distance;
	}
	
	private static double toRad(double value)
	{
		return value * Math.PI / 180;
	}
}