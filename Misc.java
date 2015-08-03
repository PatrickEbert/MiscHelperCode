import java.io.*;

public class Misc
{
	public static void log(String text)
	{
		try
		{
		FileWriter fw = new FileWriter("Log.txt",true);
		fw.write(text + "\n");
		fw.close();
		}catch(IOException ioex)
		{
			System.out.println("Logging failed: " + ioex.toString());
		}
	}
	
	public static void log(String text, String file)
	{
		try
		{
		FileWriter fw = new FileWriter(file,true);
		fw.write(text + "\n");
		fw.close();
		}catch(IOException ioex)
		{
			System.out.println("Logging failed: " + ioex.toString());
		}
	}
}