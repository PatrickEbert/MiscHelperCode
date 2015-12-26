import java.io.*;
/**
*
* Class Misc represents a collection of simple static methods which are used across the board to simplify the code and remove necessary but offtopic code from "business" logic
*
*/
public class Misc
{
	/**
	* log(String text) writes any given text to a file called 'Log.txt' in the actual working directory. If 'Log.txt' doesn't exist it will be created, if it exists the text wîll be appended. At the end a newline character will be appended
	*
	* @param text given by caller to write into a './Log.txt' ended by a newline character
	*/
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
	
	/**
	* log(String text) writes any given text to a file addressed by the file parameter in the actual working directory. If the file doesn't exist it will be created, if it exists the text wîll be appended. At the end a newline character will be appended
	*
	* @param text given by caller to write into a './[file]' ended by a newline character
	*
	* @param file the file the text should be logged in
	*/
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
	
	public static void pause(long time)
	{
		try
		{
			Thread.sleep(time);
		}catch(Exception ex)
		{
			System.out.println("Exception Misc.pause: " + ex.toString());
		}
	}
}