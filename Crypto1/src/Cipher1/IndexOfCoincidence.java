package Cipher1;

import java.util.Scanner;

public class IndexOfCoincidence
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

		// Index of Coincidence method.
		// Assuming different value of m to find keyword
		int m = 10;

		// Here we are finding the substrings for the calculation of Ic
		String SubString[] = new String[m];
		for (int i = 0; i < m; i++)
		{
			String Sub_String = "";
			int j = i;
			while (j < CipherTextLength)
			{
				Sub_String = Sub_String + CipherText.charAt(j);
				j = j + m;
			}
			SubString[i] = Sub_String;
			System.out.println("Encrypted SubString[ " + i + " ] :" + SubString[i]);
		}

		// Determining the frequency of the character and value of Ic for the
		// cipher text.
		int Frequency[] = new int[100];
		int SubStringLenght = 0;
		for (int i = 0; i < m; i++)
		{
			System.out.println(" SubString y[ " + i + " ] :" + SubString[i]);
			SubStringLenght = SubString[i].length();
			int Flag = 64;
			for (int j = 0; j < 26; j++)
			{
				Flag++;
				char Temp = (char) Flag;
				int CharacterOccurence = 0;
				for (int k = 0; k < SubStringLenght; k++)
				{
					if (SubString[i].charAt(k) == Temp)
						CharacterOccurence++;
				}
				if (CharacterOccurence != 0)
				{
					Frequency[Flag] = CharacterOccurence;
				}
			}

			// Index of Coincidence calculation.
			// Implement the formula for finding Ic.
			double Ic = 0.00;
			for (int l = 65; l < 91; l++)
			{
				double EquationNumerator = (Frequency[l]) * (Frequency[l] - 1);
				double EquationDenominator = (SubStringLenght) * (SubStringLenght - 1);
				double EquationCalculation = EquationNumerator / EquationDenominator;
				Ic = Ic + EquationCalculation;
			}
			System.out.println(" The value of Ic = " + Ic);

			// Obtaining the actual key value.
			String SubStringTemp = SubString[i];
			int KeyCharacterNumber = GenerateKey(SubStringTemp, m);
			char KeyCharacter = (char) (KeyCharacterNumber + 65);
			System.out.println("Possible character of Key[ " + i + " ] : " + KeyCharacter);
		}
	}

	private static int GenerateKey(String SubString, int m)
	{
		// Determining the frequency of the character
		int StringLenght = SubString.length();
		int Flag = 64;
		int TempCharacter[] = new int[100];
		for (int i = 0; i < 26; i++)
		{
			Flag++;
			char Temp = (char) Flag;
			int CharacterOccurence = 0;
			for (int j = 0; j < StringLenght; j++)
			{
				if (SubString.charAt(j) == Temp)
				{
					CharacterOccurence++;// counts number of character
				}
			}
			if (CharacterOccurence != 0)
			{
				TempCharacter[Flag] = CharacterOccurence;
			}
		}
		// Mapping the frequency of each character's frequency and calculating
		// Mg.
		// Here we will obtain the frequency of each character in the substring.
		double Frequency = 0.00;
		double TempStringLength = StringLenght;
		double Mg[] = new double[26];
		for (int i = 0; i < 26; i++)
		{
			Mg[i] = 0.00;
			for (int j = 65; j < 91; j++)
			{
				switch (j)
				{
				case 65:
					Frequency = 0.082;
					break;
				case 66:
					Frequency = 0.015;
					break;
				case 67:
					Frequency = 0.028;
					break;
				case 68:
					Frequency = 0.043;
					break;
				case 69:
					Frequency = 0.127;
					break;
				case 70:
					Frequency = 0.022;
					break;
				case 71:
					Frequency = 0.020;
					break;
				case 72:
					Frequency = 0.061;
					break;
				case 73:
					Frequency = 0.070;
					break;
				case 74:
					Frequency = 0.002;
					break;
				case 75:
					Frequency = 0.008;
					break;
				case 76:
					Frequency = 0.040;
					break;
				case 77:
					Frequency = 0.024;
					break;
				case 78:
					Frequency = 0.067;
					break;
				case 79:
					Frequency = 0.075;
					break;
				case 80:
					Frequency = 0.019;
					break;
				case 81:
					Frequency = 0.001;
					break;
				case 82:
					Frequency = 0.060;
					break;
				case 83:
					Frequency = 0.063;
					break;
				case 84:
					Frequency = 0.091;
					break;
				case 85:
					Frequency = 0.028;
					break;
				case 86:
					Frequency = 0.010;
					break;
				case 87:
					Frequency = 0.023;
					break;
				case 88:
					Frequency = 0.001;
					break;
				case 89:
					Frequency = 0.020;
					break;
				case 90:
					Frequency = 0.001;
					break;
				}
				int Ig = 0;
				if (j + i <= 90)
				{
					Ig = j + i;
				} else
				{
					Ig = j + i - 26;
				}
				// Applying the formula to find Mg.
				double MNumerator = Frequency * TempCharacter[Ig];
				double MDenominator = TempStringLength;
				double Temp = MNumerator / MDenominator;
				Mg[i] = Mg[i] + Temp;
			}
		}

		// Select the maximum frequency which will result in the final key
		// character.
		int TempFlag = 0;
		double Check = Mg[0];
		for (int k = 1; k < 26; k++)
		{
			if (Check < Mg[k])
			{
				Check = Mg[k];
				TempFlag = k;
			}
		}
		return TempFlag;
	}
}
