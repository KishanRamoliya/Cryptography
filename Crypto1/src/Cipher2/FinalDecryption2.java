// Permutation Cipher implementation.

package Cipher2;

import java.util.Scanner;

public class FinalDecryption2
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
		
		// Performing the swapping of the characters according to the key.
		for (int i = 0; i < CipherTextLength;)
		{
			char CipherTemp[] = new char[9];
			char PlainTemp[] = new char[9];
			for (int j = 0; j < 9; j++)
			{
				CipherTemp[j] = CipherText.charAt(i);
				i++;
			}
			PlainTemp[0] = CipherTemp[3];
			PlainText = PlainText + PlainTemp[0];
			PlainTemp[1] = CipherTemp[4];
			PlainText = PlainText + PlainTemp[1];
			PlainTemp[2] = CipherTemp[0];
			PlainText = PlainText + PlainTemp[2];
			PlainTemp[3] = CipherTemp[8];
			PlainText = PlainText + PlainTemp[3];
			PlainTemp[4] = CipherTemp[6];
			PlainText = PlainText + PlainTemp[4];
			PlainTemp[5] = CipherTemp[1];
			PlainText = PlainText + PlainTemp[5];
			PlainTemp[6] = CipherTemp[7];
			PlainText = PlainText + PlainTemp[6];
			PlainTemp[7] = CipherTemp[5];
			PlainText = PlainText + PlainTemp[7];
			PlainTemp[8] = CipherTemp[2];
			PlainText = PlainText + PlainTemp[8];
			
			// Print the plain text.
			if (PlainText.length() == CipherTextLength)
			{
				System.out.println(" Plain Text is : " + PlainText.toLowerCase());
			}
		}
	}
}
