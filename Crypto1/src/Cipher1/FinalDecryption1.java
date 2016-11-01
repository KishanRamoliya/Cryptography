// Vigenere Cipher implementation.

package Cipher1;

import java.util.Scanner;

public class FinalDecryption1
{
	public static void main(String args[])
	{
		// Take the input of the encrypted text from the user.
		System.out.println(" Enter the striped cipher text : ");
		Scanner ScanText = new Scanner(System.in);
		String CipherText = ScanText.nextLine();
		
		// Calculate the length of the text.
		int CipherTextLength = CipherText.length();
		System.out.println(" Lenght of the entered cipher text is = " + CipherTextLength);
		
		String PlainText = "";
		int Difference = 0;
		int KeyCharacterNumber = 1;
		for (int i = 0; i < CipherTextLength; i++)
		{
			// Get the original first character from cipher text.
			char OriginalCharacter = CipherText.charAt(i);
			
			// Get the ASCII value of the character.
			int OriginalCharacterValue = (int) OriginalCharacter;
			
			// Obtain the number to be added to the character.
			switch (KeyCharacterNumber)
			{
				case 1:
					Difference = 23;
					break;
				case 2:
					Difference = 0;
					break;
				case 3:
					Difference = 9;
					break;
				case 4:
					Difference = 16;
					break;
				case 5:
					Difference = 16;
					break;
				case 6:
					Difference = 13;
					break;
				case 7:
					Difference = 18;
					break;
				case 8:
					Difference = 20;
					break;
				case 9:
					Difference = 19;
					break;
				case 10:
					Difference = 7;
					break;
			}
			
			// Obtain the ASCII value of the plain text character.
			int PlainTextCharacterValue = OriginalCharacterValue + Difference;
			
			// Adjust the values.
			if (PlainTextCharacterValue > 90)
			{
				PlainTextCharacterValue = PlainTextCharacterValue - 26;
			}
			
			// Make the key character number as per requirement of the loop.
			if (KeyCharacterNumber == 10)
			{
				KeyCharacterNumber = KeyCharacterNumber + 1 - 10;
			}
			else if (KeyCharacterNumber > 10)
			{
				KeyCharacterNumber = KeyCharacterNumber - 10;
			}
			else
			{
				KeyCharacterNumber++;
			}
			
			// Obtaining the plain text.
			PlainText = PlainText + Character.toString((char) PlainTextCharacterValue);
			if (PlainText.length() == CipherTextLength)
			{
				System.out.println(" Plain Text is : " + PlainText.toLowerCase());
			}
		}
	}
}
