import java.io.*;
import java.util.*;

public class FileHelper
{
	public static List<String>readFileToArray(String filename, String charset)
	{
		List<String>lines = new ArrayList<String>();
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),charset));
			String line;
			while((line = br.readLine())!=null)
			{
				lines.add(line);
			}
			br.close();
		}catch(Exception ex){System.out.println(ex.toString());}
		return lines;
	}
	
	public static String getHeader(String filename,String charset)
	{
		String header = null;
		try
		{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),charset));
		header = br.readLine();
		br.close();
		}catch(Exception ex){System.out.println(ex.toString());}
		return header;
	}
	
	public static int getDelimiterCount(String filename,String charset, String delimiter)
	{
		return getHeader(filename,charset).split(delimiter).length-1;
	}
	
	public static boolean checkIntegrity(String filename,String charset, String delimiter)
	{
		boolean integritiy = true;
		int delimiterCount = getDelimiterCount(filename,charset,delimiter);
		try
		{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),charset));
		String line;
		while((line = br.readLine())!= null)
		{
			if(delimiterCount != line.split(delimiter).length-1)
			{
				integritiy = false;
				break;
			}
		}
		br.close();
		}catch(Exception ex){System.out.println(ex.toString());}
		return integritiy;
	}
}