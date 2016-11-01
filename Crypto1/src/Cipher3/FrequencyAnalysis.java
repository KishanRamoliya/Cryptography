package Cipher3;

import java.util.Scanner;

public class FrequencyAnalysis
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
		
		int CharacterFrequency[] = new int[26];
		for (int i = 0; i <= 25; i++)
		{
			CharacterFrequency[i] = 0;
		}
		for (int i = 0; i < CipherTextLength; i++)
		{
			int CharacterValue = (int) CipherText.charAt(i);
			switch (CharacterValue)
			{
				case 65:
					CharacterFrequency[0]++;
					break;
				case 66:
					CharacterFrequency[1]++;
					break;
				case 67:
					CharacterFrequency[2]++;
					break;
				case 68:
					CharacterFrequency[3]++;
					break;
				case 69:
					CharacterFrequency[4]++;
					break;
				case 70:
					CharacterFrequency[5]++;
					break;
				case 71:
					CharacterFrequency[6]++;
					break;
				case 72:
					CharacterFrequency[7]++;
					break;
				case 73:
					CharacterFrequency[8]++;
					break;
				case 74:
					CharacterFrequency[9]++;
					break;
				case 75:
					CharacterFrequency[10]++;
					break;
				case 76:
					CharacterFrequency[11]++;
					break;
				case 77:
					CharacterFrequency[12]++;
					break;
				case 78:
					CharacterFrequency[13]++;
					break;
				case 79:
					CharacterFrequency[14]++;
					break;
				case 80:
					CharacterFrequency[15]++;
					break;
				case 81:
					CharacterFrequency[16]++;
					break;
				case 82:
					CharacterFrequency[17]++;
					break;
				case 83:
					CharacterFrequency[18]++;
					break;
				case 84:
					CharacterFrequency[19]++;
					break;
				case 85:
					CharacterFrequency[20]++;
					break;
				case 86:
					CharacterFrequency[21]++;
					break;
				case 87:
					CharacterFrequency[22]++;
					break;
				case 88:
					CharacterFrequency[23]++;
					break;
				case 89:
					CharacterFrequency[24]++;
					break;
				case 90:
					CharacterFrequency[25]++;
					break;
			}
		}
		System.out.println(" Frequency of the characters in the cipher text are given below.");
		for (int i = 0; i < 26; i++)
		{
			System.out.println(" Character " + (char) (i + 65) + " occurs " + CharacterFrequency[i] + " times.");
		}
	}
}
