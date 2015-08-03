/**
* This class defines a point om the earth in longitude/latitude coordinates (WGS84). Beyond that it provides some methods to perform several geospatial point related calculations, such as distances between points.
*
* @author Patrick Ebert
*
*/
public class Geolocation
{
	// STATIC METHODS FOR DIFFERENT PURPOSES
	/**
	* calculateDistance
	*
	* calculates the Distance between to points on the earth using the haversine formula.
	*
	* @param longitudeOrigin the longitude (WGS84) value for the origin
	*
	* @param latitudeOrigin the longitude (WGS84) value for the origin
	*
	* @param longitudeDestination the longitude (WGS84) value for the destination
	*
	* @param latitudeDestination the longitude (WGS84) value for the destination
	*
	* @return distance in kilometre
	*/
	public static double calculateDistance(double longitudeOrigin, double latitudeOrigin, double longitudeDestination, double latitudeDestination)
	{
		int R = 6371; // Earth radius
		double latDist = toRadi(latitudeDestination - latitudeOrigin);
		double lngDist = toRadi(longitudeDestination - longitudeOrigin);
		double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
					+ Math.cos(toRadi(latitudeOrigin)) * Math.cos(toRadi(latitudeDestination))
					* Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = c * R;
		return distance;
	}
	
	/**
	* calculateDistance
	*
	* calculates the Distance between to points on the earth using the haversine formula.
	*
	* @param origin Geolocation of the origin
	*
	* @param destination Geolocation of the destination
	*
	* @return distance in kilometre
	*/
	public static double calculateDistance(Geolocation origin, Geolocation destination)
	{
		int R = 6371; // Earth radius
		double latDist = toRadi(destination.getLatitude() - origin.getLatitude());
		double lngDist = toRadi(destination.getLongitude() - origin.getLongitude());
		double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
					+ Math.cos(toRadi(origin.getLatitude())) * Math.cos(toRadi(destination.getLatitude()))
					* Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = c * R;
		return distance;
	}
	
	private static double toRadi(double value)
	{
		return value * Math.PI / 180;
	}

	// E V E R Y T H I N G  O B J E C T  O R I E N T A T E D  O R  A T  L E A S T  N O N - S T A T I C
	private double longitude;
	private double latitude;
	
	/**
	* Constructor for Geolocations with longitude and latitude (WGS84) values. There are no checks for values out of range and points do not have an elevation so far.
	* 
	* @param longitude sets the longitude (WGS84) value for the Geolocation
	*
	* @param latitude sets the latitude (WGS84) value for the Geolocation
	*/
	public Geolocation(double longitude, double latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	* returns the locations longitude (WGS84)
	*
	* @return longitude
	*/
	public double getLongitude()
	{
		return this.longitude;
	}
	
	/**
	* returns the locations latitude (WGS84)
	*
	* @return latitude
	*/
	public double getLatitude()
	{
		return this.latitude;
	}
	
	/**
	* calculates the distance to a specific point on the earth using the haversine formula
	*
	* @param destination target location
	*
	* @return distance in kilometres
	*/
	public double getDistance(Geolocation destination)
	{
		int R = 6371; // Earth radius
		double latDist = toRad(destination.getLatitude() - this.getLatitude());
		double lngDist = toRad(destination.getLongitude() - this.getLongitude());
		double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
					+ Math.cos(toRad(this.getLatitude())) * Math.cos(toRad(destination.getLatitude()))
					* Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = c * R;
		return distance;
	}
	
	/**
	* calculates the distance to a specific point on the earth using the haversine formula
	*
	* @param longitude longitude (WGS84) of the destination
	*
	* @param latitude latitude (WGS84) of the destination
	*
	* @return distance in kilometres
	*/
	public double getDistance(double longitude, double latitude)
	{
		Geolocation destination = new Geolocation(longitude,latitude);
		int R = 6371; // Earth radius
		double latDist = toRad(destination.getLatitude() - this.getLatitude());
		double lngDist = toRad(destination.getLongitude() - this.getLongitude());
		double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
					+ Math.cos(toRad(this.getLatitude())) * Math.cos(toRad(destination.getLatitude()))
					* Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = c * R;
		return distance;
	}
	
	/**
	* returns X Value in CH1903 (swiss military coordinates)
	*
	* @return X Value in CH1903
	*/
	public int getCH1903XValue()
	{
		int latH = (int)latitude;
		int latM = (int)((latitude - latH)*60);
		int latS = (int)((((latitude - latH)*60)-latM)*60);
		int latSexaGesSec = latH * 3600 + latM * 60 + latS;
		double _latSexaGesSec = ((double)latSexaGesSec - 169028.66)/10000;
		int lngH = (int)longitude;
		int lngM = (int)((longitude - lngH)*60);
		int lngS = (int)((((longitude - lngH)*60)-lngM)*60);
		int lngSexaGesSec = lngH * 3600 + lngM * 60 + lngS;
		double _lngSexaGesSec = ((double)lngSexaGesSec - 26782.5)/10000;
		return (int)(200147.07 + 308807.95 * _latSexaGesSec + 3745.25 * _lngSexaGesSec * _lngSexaGesSec + 76.63 * _latSexaGesSec * _latSexaGesSec + 119.79 * _latSexaGesSec * _latSexaGesSec * _latSexaGesSec - 194.56 * _lngSexaGesSec * _lngSexaGesSec * _latSexaGesSec);
	}
	
	/**
	* returns Y Value in CH1903 (swiss military coordinates)
	*
	* @return Y Value in CH1903
	*/
	public int getCH1903YValue()
	{
		int latH = (int)latitude;
		int latM = (int)((latitude - latH)*60);
		int latS = (int)((((latitude - latH)*60)-latM)*60);
		int latSexaGesSec = latH * 3600 + latM * 60 + latS;
		double _latSexaGesSec = ((double)latSexaGesSec - 169028.66)/10000;
		int lngH = (int)longitude;
		int lngM = (int)((longitude - lngH)*60);
		int lngS = (int)((((longitude - lngH)*60)-lngM)*60);
		int lngSexaGesSec = lngH * 3600 + lngM * 60 + lngS;
		double _lngSexaGesSec = ((double)lngSexaGesSec - 26782.5)/10000;
		return (int)(600072.37 + 211455.93 * _lngSexaGesSec - 10938.51 * _lngSexaGesSec * _latSexaGesSec - 0.36 * _lngSexaGesSec * _latSexaGesSec * _latSexaGesSec - 44.54 * _lngSexaGesSec * _lngSexaGesSec * _lngSexaGesSec);
	}
	
	private double toRad(double value)
	{
		return value * Math.PI / 180;
	}
}