/*
Functioning Call
java BatchGeocoding -f "GooGeoSample.csv" "UTF-8" "[1], [3] [11]" "," "google|json|" "resGooGeoSample.csv" "[0];ZHOpenDataAdresses;GOOGLE" ";"
*/

public class BatchGeocoding
{
	public static void main(String[] args)
	{
		System.out.println("Start BatchCoding...");
		if(args.length > 0)
		{
			switch(args[0])
			{
			case "-f":
				// All code
				if(args.length == 9)
				{
					GeoCoder.geocodeFile(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
				}
				else
				{
					System.out.println("please use -f sourcefile encoding sourceStringPattern sourceDelimiter geocodeControl targetFilename targetStringPrefixPattern targetDelimiter");
				}
				break;
			default:
				System.out.println("please use -f sourcefile encoding sourceStringPattern sourceDelimiter geocodeControl targetFilename targetStringPrefixPattern targetDelimiter");
				break;
			}
		}
		else
		{
			System.out.println("please use -f sourcefile encoding sourceStringPattern sourceDelimiter geocodeControl targetFilename targetStringPrefixPattern targetDelimiter");
		}
	}
}