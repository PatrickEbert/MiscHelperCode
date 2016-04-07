import java.util.*;

public class StringHelper
{
	public static String evaluatePattern(String line, String pattern, String delimiter)
	{
		String result = "";
		boolean ready = false;
		int offset = 0;
		int lastEndOffset = 0;
		while(!ready)
		{
			if((pattern.indexOf("{", offset) == -1)&&(pattern.indexOf("[", offset) == -1))
			{
				result += pattern.substring(lastEndOffset);
				ready = true;
				break;
			}
			int noTrimIndex = pattern.indexOf("{", offset);
			int trimIndex = pattern.indexOf("[", offset);
			if(noTrimIndex == -1){noTrimIndex = Integer.MAX_VALUE;}
			if(trimIndex == -1){noTrimIndex = Integer.MAX_VALUE;}
			if(noTrimIndex < trimIndex)
			{
				offset = noTrimIndex;
				int endOffset = pattern.indexOf("}",offset);
				result += pattern.substring(lastEndOffset,offset);
				if(line.split(delimiter).length > Integer.parseInt(pattern.substring(offset + 1, endOffset)))
				{
					result += line.split(delimiter)[Integer.parseInt(pattern.substring(offset + 1, endOffset))];
				}
				lastEndOffset = endOffset + 1;
			}
			else
			{
				offset = trimIndex;
				int endOffset = pattern.indexOf("]",offset);
				result += pattern.substring(lastEndOffset,offset);
				if(line.split(delimiter).length > Integer.parseInt(pattern.substring(offset + 1, endOffset)))
				{
					result += line.split(delimiter)[Integer.parseInt(pattern.substring(offset + 1, endOffset))].trim();
				}
				lastEndOffset = endOffset + 1;
			}
			offset++;
		}
		return result;
	}
	
	public static boolean stringContainsStringFromList(String input,List<String> listToCompare)
	{
		boolean containsItem = false;
		Iterator i = listToCompare.iterator();
		while(i.hasNext())
		{
			if(input.indexOf((String)i.next()) >= 0)
			{
				containsItem = true;
				break;
			}
		}
		return containsItem;
	}
	
	public static String stringFromListContainedInString(String input,List<String> listToCompare)
	{
		String containingItem = "";
		Iterator i = listToCompare.iterator();
		while(i.hasNext())
		{
			String item = (String)i.next();
			if(input.indexOf(item) >= 0)
			{
				containingItem = item;
				break;
			}
		}
		return containingItem;
	}
	
	public static boolean stringIsInteger(String testString)
	{
		try
		{
			Integer.parseInt(testString.trim());
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
}