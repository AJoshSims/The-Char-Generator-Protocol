package common;

public class MessnAround
{
	public static void main(String[] args)
	{
//		String input = "NNANbuttseks\r\n";
//		
//		String[] inputSplit = input.split("\r\n", 0);
//		
//		String output = inputSplit[0];
//		
//		System.out.println("input: " + input);
//		
//		System.out.println("");
//		
//		System.out.println("inputArray: ");
//		for (int index = 0; index < inputSplit.length; ++index)
//		{
//			System.out.println(inputSplit[index]);
//		}
//	
//		System.out.println("");
//		
//		System.out.println("inputSplit[0]: " + output);
		
		
//		String receivedString = "\n";
//		boolean valid = false;
//		int indexOfCurrentChar = 0;
//		int indexOfNextChar = 0;
//		final int indexOfLastChar = receivedString.length() - 1;
//		for (
//			indexOfCurrentChar = 0; 
//			indexOfCurrentChar < indexOfLastChar;
//			++indexOfCurrentChar)
//		{
//			indexOfNextChar = indexOfCurrentChar + 1;
//			if (
//				receivedString.charAt(indexOfCurrentChar) == '\r'
//				&& receivedString.charAt(indexOfNextChar) == '\n')
//			{
//				valid = true;
//				break;
//			}
//		}
//		
//		if (valid == true)
//		{
//			System.out.println("String \"" + receivedString.substring(0, indexOfCurrentChar) + "\" is valid.");
//		}
//		else
//		{
//			System.out.println("String is not valid.");
//		}
		
		
		
//		String om = "om";
//		Character g = 'g';
//		
//		om += g;
//		
//		System.out.println(om);
		
		
		
		
		char[] stuffChars = {'N', '\r', '\n'};
		
		String stuffString = new String(stuffChars);
		
		String before = stuffString + "that was the string";
		
		System.out.println("Before substring: " + before);
		
		stuffString = stuffString.substring(0, stuffString.length() - 2);
		
		String after = stuffString + "that was the string";
		
		System.out.println("After substring: " + after);
		
	}
}
