package Cipher3;

import java.util.Scanner;

public class StripCipher
{
	public static void main(String[] args)
	{
		// Take the input of the encrypted text from the user.
		System.out.println(" Enter the cipher text : ");
		Scanner ScanText = new Scanner(System.in);
		String OriginalCipherText = ScanText.nextLine();

		// Eliminate the wide space and special characters present in the input
		// text.
		String CipherText = OriginalCipherText.replaceAll("\\s+", "");
		CipherText = OriginalCipherText.replaceAll("[^a-zA-Z]+", "");
		System.out.println(" Striped Cipher text is : " + CipherText);

		// Calculate the length of the text.
		int CipherTextLength = CipherText.length();
		System.out.println(" Lenght of the cipher text is : " + CipherTextLength);
	}
}
