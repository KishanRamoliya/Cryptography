package Cipher1;

import java.util.Scanner;

public class KasiskiTest
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

		// Pair of first three characters are obtained from the cipher text as
		// mentioned in the Kasiski Test.
		String CipherTextPair = CipherText.substring(0, 3);

		// Performing Kasiski Test to determine the length of key i.e. m
		// This will give us all the occurence of the identical segments
		// specified in the CipherTextPair.
		System.out.println(" Occurence of the pair " + CipherTextPair + " is given below.");
		int CipherTextPairIndex[] = new int[CipherTextLength];
		for (int i = -1, j = 0; (i = CipherText.indexOf(CipherTextPair, i + 1)) != -1; j++)
		{
			CipherTextPairIndex[j] = i;
			System.out.println(" Occurence number " + j + " at the index : " + i);
		}
		for (int i = 0; i < 6; i++)
		{
			System.out.println("CipherTextPairIndex[" + i + "] = " + CipherTextPairIndex[i]);
		}

		// We have an array CipherTextPairIndex which have the distance between
		// the starting positions of the repeating pairs.
		// We can determining the value of m by obtaining GCD of the indexes of
		// the repeating pairs.
		// int m = CipherTextPairIndex[0];
		int m = 0;
		for (int i = 0; i < CipherTextPairIndex.length; i++)
		{
			m = GetGCD(m, CipherTextPairIndex[i]);
		}
		System.out.println(" GCD of the indexes i.e. m = " + m);
	}

	// Gives the GCD of the two given numbers.
	private static int GetGCD(int FirstNumber, int SecondNumber)
	{
		while (FirstNumber != 0 && SecondNumber != 0)
		{
			int Temp = SecondNumber;
			SecondNumber = FirstNumber % SecondNumber;
			FirstNumber = Temp;
		}
		return FirstNumber + SecondNumber;
	}
}
