// Substitution Cipher implementation.

package Cipher3;

import java.util.Scanner;

public class FinalDecryption3
{
	public static void main(String[] args)
	{
		// Take the input of the encrypted text from the user.
		System.out.println(" Enter the striped cipher text : ");
		Scanner ScanText = new Scanner(System.in);
		String CipherText = ScanText.nextLine();
		
		// Calculate the length of the text.
		int CipherTextLength = CipherText.length();
		System.out.println(" Lenght of the entered cipher text is = " + CipherTextLength);
		
		String PlainText = "";
		
		// Substitute the character of the cipher text by original character.
		for (int i = 0; i < CipherTextLength; i++)
		{
			char PlainTemp;
			switch (CipherText.charAt(i))
			{
				case 'A':
					PlainTemp = 'K';
					PlainText = PlainText + PlainTemp;
					break;
				case 'B':
					PlainTemp = 'X';
					PlainText = PlainText + PlainTemp;
					break;
				case 'C':
					PlainTemp = 'V';
					PlainText = PlainText + PlainTemp;
					break;
				case 'D':
					PlainTemp = 'M';
					PlainText = PlainText + PlainTemp;
					break;
				case 'E':
					PlainTemp = 'C';
					PlainText = PlainText + PlainTemp;
					break;
				case 'F':
					PlainTemp = 'N';
					PlainText = PlainText + PlainTemp;
					break;
				case 'G':
					PlainTemp = 'O';
					PlainText = PlainText + PlainTemp;
					break;
				case 'H':
					PlainTemp = 'P';
					PlainText = PlainText + PlainTemp;
					break;
				case 'I':
					PlainTemp = 'H';
					PlainText = PlainText + PlainTemp;
					break;
				case 'J':
					PlainTemp = 'Q';
					PlainText = PlainText + PlainTemp;
					break;
				case 'K':
					PlainTemp = 'R';
					PlainText = PlainText + PlainTemp;
					break;
				case 'L':
					PlainTemp = 'S';
					PlainText = PlainText + PlainTemp;
					break;
				case 'M':
					PlainTemp = 'Z';
					PlainText = PlainText + PlainTemp;
					break;
				case 'N':
					PlainTemp = 'Y';
					PlainText = PlainText + PlainTemp;
					break;
				case 'O':
					PlainTemp = 'I';
					PlainText = PlainText + PlainTemp;
					break;
				case 'P':
					PlainTemp = 'J';
					PlainText = PlainText + PlainTemp;
					break;
				case 'Q':
					PlainTemp = 'A';
					PlainText = PlainText + PlainTemp;
					break;
				case 'R':
					PlainTemp = 'D';
					PlainText = PlainText + PlainTemp;
					break;
				case 'S':
					PlainTemp = 'L';
					PlainText = PlainText + PlainTemp;
					break;
				case 'T':
					PlainTemp = 'E';
					PlainText = PlainText + PlainTemp;
					break;
				case 'U':
					PlainTemp = 'G';
					PlainText = PlainText + PlainTemp;
					break;
				case 'V':
					PlainTemp = 'W';
					PlainText = PlainText + PlainTemp;
					break;
				case 'W':
					PlainTemp = 'B';
					PlainText = PlainText + PlainTemp;
					break;
				case 'X':
					PlainTemp = 'U';
					PlainText = PlainText + PlainTemp;
					break;
				case 'Y':
					PlainTemp = 'F';
					PlainText = PlainText + PlainTemp;
					break;
				case 'Z':
					PlainTemp = 'T';
					PlainText = PlainText + PlainTemp;
					break;
			}
			// Print the plain text.
			if (PlainText.length() == CipherTextLength)
			{
				System.out.println(" Plain Text is : " + PlainText.toLowerCase());
			}
		}
	}
}
