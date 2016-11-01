// DES implementation.

package Question2;

import java.util.Scanner;

public class DESDecryption
{
	public static void main(String[] args)
	{
		//
		// Take input of Ciphertext in HEX.
		//
		Scanner Sca = new Scanner(System.in);
		System.out.println(" Enter the ciphertext in Hex form : ");
		String CipherText = Sca.nextLine();
		
		//
		// Convert Hex Ciphertext to Binary.
		//
		String BinaryCipher;
		BinaryCipher = ConvertToBinary(CipherText);
		System.out.println(" Ciphertext in Binary form : " + BinaryCipher);
		
		//
		// Initial Permutation for Ciphertext.
		//
		String PermutedCipher;
		PermutedCipher = InitialPermutation(BinaryCipher);
		System.out.println(" Ciphertext in after initial permutation : " + PermutedCipher);
		
		//
		// Enter Key and convert it to binary.
		//
		System.out.println(" Enter the key in Hex form : ");
		String KeyHex = Sca.nextLine();
		String BinaryKey = ConvertToBinary(KeyHex);
		System.out.println(" Key in Binary form : " + BinaryKey);
		
		//
		// PC1 for key.
		//
		String KeyPC1 = KeyPC1(BinaryKey);
		System.out.println(" Key after PC1 : " + KeyPC1);
		System.out.println(" Key length after PC1 : " + KeyPC1.length());
		
		//
		// Key generation.
		//
		System.out.println();
		String FinalKeyString[] = new String[16];
		String KeyString[] = new String[16];
		for (int i = 0; i < 16; i++)
		{
			if (i == 0)
			{
				int Flag = i + 1;
				KeyString[i] = GenerateKey(KeyPC1, Flag);
			}
			else
			{
				int Flag = i + 1;
				int KeyIndex = i - 1;
				KeyString[i] = GenerateKey(KeyString[KeyIndex], Flag);
			}
			System.out.println(" Key [ " + i + " ] : " + KeyString[i]);
			FinalKeyString[i] = KeyPC2(KeyString[i]);
			System.out.println(" Key [ " + i + " ] after PC2 : " + FinalKeyString[i]);
			System.out.println(" Key [ " + i + " ] length : " + FinalKeyString[i].length());
			System.out.println();
		}
		
		//
		// Decryption.
		//
		// Left side of Ciphertext.
		String LeftCipherText = "";
		System.out.println(" 64 bit Cipher text : " + PermutedCipher);
		for (int i = 0; i < 32; i++)
		{
			LeftCipherText = LeftCipherText + PermutedCipher.charAt(i);
		}
		System.out.println(" 32 bit Left Cipher text : " + LeftCipherText);
		// Right side of Ciphertext.
		String RightCipherText = "";
		for (int i = 0; i < 32; i++)
		{
			RightCipherText = RightCipherText + PermutedCipher.charAt(i + 32);
		}
		System.out.println(" 32 bit Right Cipher text : " + RightCipherText);
		
		// Compute DEF function with Right side of Ciphertext and key in reverse
		// order as input.
		for (int i = 0; i < 16; i++)
		{
			int KeyIndex = 15 - i;
			String RightFunctionOutput = DESFunction(RightCipherText, FinalKeyString[KeyIndex]);
			String XORWithLeft = "";
			int TempXORString[] = new int[48];
			
			// Perform XOR with left side of Ciphertext.
			for (int j = 0; j < 32; j++)
			{
				int TempA, TempB;
				if (LeftCipherText.charAt(j) == '1')
				{
					TempA = 1;
				}
				else
				{
					TempA = 0;
				}
				if (RightFunctionOutput.charAt(j) == '1')
				{
					TempB = 1;
				}
				else
				{
					TempB = 0;
				}
				TempXORString[j] = TempA ^ TempB;
			}
			for (int k = 0; k < 32; k++)
			{
				if (TempXORString[k] == 1)
				{
					XORWithLeft = XORWithLeft + '1';
				}
				else
				{
					XORWithLeft = XORWithLeft + '0';
				}
			}
			System.out.println(" XOR with left : " + XORWithLeft);
			System.out.println(" XOR with left length : " + XORWithLeft.length());
			LeftCipherText = RightCipherText;
			RightCipherText = XORWithLeft;
		}
		
		// Perform reverse IP.
		String FinalPlainText = LeftCipherText + RightCipherText;
		String PlainText = ReverseInitialPermutation(FinalPlainText);
		
		//
		// Enter IV
		//
		System.out.println(" Enter the IV in Hex form : ");
		String IVText = Sca.nextLine();
		String BinaryIVText;
		BinaryIVText = ConvertToBinary(IVText);
		
		//
		// XOR with IV.
		//
		String XORWithIV = "";
		int TempXORString[] = new int[64];
		for (int j = 0; j < 64; j++)
		{
			int TempA, TempB;
			if (BinaryIVText.charAt(j) == '1')
			{
				TempA = 1;
			}
			else
			{
				TempA = 0;
			}
			if (PlainText.charAt(j) == '1')
			{
				TempB = 1;
			}
			else
			{
				TempB = 0;
			}
			TempXORString[j] = TempA ^ TempB;
		}
		for (int k = 0; k < 64; k++)
		{
			if (TempXORString[k] == 1)
			{
				XORWithIV = XORWithIV + '1';
			}
			else
			{
				XORWithIV = XORWithIV + '0';
			}
		}
		
		// Final Plaintext.
		System.out.println(" Plain Text : " + XORWithIV);
		
	}
	
	//
	// DES function.
	//
	public static String DESFunction(String InputString, String Key)
	{
		String FinalOutput = "";
		
		// Expand the input.
		int E[] = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
		String PermutedOutput = "";
		for (int i = 0; i < 48; i++)
		{
			PermutedOutput = PermutedOutput + Character.toString(InputString.charAt((E[i] - 1)));
		}
		
		// XOR with key.
		String XORString = "";
		int TempXORString[] = new int[48];
		for (int i = 0; i < 48; i++)
		{
			int TempA, TempB;
			if (PermutedOutput.charAt(i) == '1')
			{
				TempA = 1;
			}
			else
			{
				TempA = 0;
			}
			if (Key.charAt(i) == '1')
			{
				TempB = 1;
			}
			else
			{
				TempB = 0;
			}
			TempXORString[i] = TempA ^ TempB;
		}
		for (int i = 0; i < 48; i++)
		{
			if (TempXORString[i] == 1)
			{
				XORString = XORString + '1';
			}
			else
			{
				XORString = XORString + '0';
			}
		}
		System.out.println(" XORed String " + XORString);
		
		// Divide in B blocks.
		String B[] = new String[8];
		B[0] = XORString.substring(0, 6);
		System.out.println(" B [ 0 ] : " + B[0]);
		B[1] = XORString.substring(6, 12);
		System.out.println(" B [ 1 ] : " + B[1]);
		B[2] = XORString.substring(12, 18);
		System.out.println(" B [ 2 ] : " + B[2]);
		B[3] = XORString.substring(18, 24);
		System.out.println(" B [ 3 ] : " + B[3]);
		B[4] = XORString.substring(24, 30);
		System.out.println(" B [ 4 ] : " + B[4]);
		B[5] = XORString.substring(30, 36);
		System.out.println(" B [ 5 ] : " + B[5]);
		B[6] = XORString.substring(36, 42);
		System.out.println(" B [ 6 ] : " + B[6]);
		B[7] = XORString.substring(42, 48);
		System.out.println(" B [ 7 ] : " + B[7]);
		
		// Compute with S-Box.
		int S1[][] = { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
				{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };
		int S2[][] = { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
				{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };
		int S3[][] = { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
				{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
		int S4[][] = { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
				{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };
		int S5[][] = { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
				{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };
		int S6[][] = { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
				{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };
		int S7[][] = { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
				{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };
		int S8[][] = { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
				{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };
				
		String C[] = new String[8];
		for (int i = 0; i < 8; i++)
		{
			String Temp = B[i];
			String Row;
			String Column;
			Row = Character.toString(Temp.charAt(0)) + Character.toString(Temp.charAt(5));
			Column = Character.toString(Temp.charAt(1)) + Character.toString(Temp.charAt(2)) + Character.toString(Temp.charAt(3)) + Character.toString(Temp.charAt(4));
			int RowNumber = 0;
			switch (Row)
			{
				case "00":
					RowNumber = 0;
					break;
				case "01":
					RowNumber = 1;
					break;
				case "10":
					RowNumber = 2;
					break;
				case "11":
					RowNumber = 3;
					break;
			}
			int ColumnNumber = 0;
			switch (Column)
			{
				case "0000":
					ColumnNumber = 0;
					break;
				case "0001":
					ColumnNumber = 1;
					break;
				case "0010":
					ColumnNumber = 2;
					break;
				case "0011":
					ColumnNumber = 3;
					break;
				case "0100":
					ColumnNumber = 4;
					break;
				case "0101":
					ColumnNumber = 5;
					break;
				case "0110":
					ColumnNumber = 6;
					break;
				case "0111":
					ColumnNumber = 7;
					break;
				case "1000":
					ColumnNumber = 8;
					break;
				case "1001":
					ColumnNumber = 9;
					break;
				case "1010":
					ColumnNumber = 10;
					break;
				case "1011":
					ColumnNumber = 11;
					break;
				case "1100":
					ColumnNumber = 12;
					break;
				case "1101":
					ColumnNumber = 13;
					break;
				case "1110":
					ColumnNumber = 14;
					break;
				case "1111":
					ColumnNumber = 15;
					break;
			}
			int SNumber = 0;
			switch (i)
			{
				case 0:
					SNumber = S1[RowNumber][ColumnNumber];
					break;
				case 1:
					SNumber = S2[RowNumber][ColumnNumber];
					break;
				case 2:
					SNumber = S3[RowNumber][ColumnNumber];
					break;
				case 3:
					SNumber = S4[RowNumber][ColumnNumber];
					break;
				case 4:
					SNumber = S5[RowNumber][ColumnNumber];
					break;
				case 5:
					SNumber = S6[RowNumber][ColumnNumber];
					break;
				case 6:
					SNumber = S7[RowNumber][ColumnNumber];
					break;
				case 7:
					SNumber = S8[RowNumber][ColumnNumber];
					break;
			}
			switch (SNumber)
			{
				case 0:
					C[i] = "0000";
					break;
				case 1:
					C[i] = "0001";
					break;
				case 2:
					C[i] = "0010";
					break;
				case 3:
					C[i] = "0011";
					break;
				case 4:
					C[i] = "0100";
					break;
				case 5:
					C[i] = "0101";
					break;
				case 6:
					C[i] = "0110";
					break;
				case 7:
					C[i] = "0111";
					break;
				case 8:
					C[i] = "1000";
					break;
				case 9:
					C[i] = "1001";
					break;
				case 10:
					C[i] = "1010";
					break;
				case 11:
					C[i] = "1011";
					break;
				case 12:
					C[i] = "1100";
					break;
				case 13:
					C[i] = "1101";
					break;
				case 14:
					C[i] = "1110";
					break;
				case 15:
					C[i] = "1111";
					break;
			}
			System.out.println(" C [" + i + "]: " + C[i]);
		}
		String FinalC = "";
		FinalC = C[0] + C[1] + C[2] + C[3] + C[4] + C[5] + C[6] + C[7];
		System.out.println(" Final C : " + FinalC);
		String PermutedC = CPermute(FinalC);
		return PermutedC;
	}
	
	//
	// C Permutation.
	//
	public static String CPermute(String InputString)
	{
		String PermutedOutput = "";
		int P[] = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
		for (int i = 0; i < 32; i++)
		{
			PermutedOutput = PermutedOutput + Character.toString(InputString.charAt((P[i] - 1)));
		}
		return PermutedOutput;
	}
	
	//
	// Convert to Binary.
	//
	public static String ConvertToBinary(String HexInput)
	{
		String BinaryOutput = "";
		String Temp = "";
		for (int i = 0; i < HexInput.length(); i++)
		{
			switch (HexInput.charAt(i))
			{
				case '0':
					Temp = "0000";
					break;
				case '1':
					Temp = "0001";
					break;
				case '2':
					Temp = "0010";
					break;
				case '3':
					Temp = "0011";
					break;
				case '4':
					Temp = "0100";
					break;
				case '5':
					Temp = "0101";
					break;
				case '6':
					Temp = "0110";
					break;
				case '7':
					Temp = "0111";
					break;
				case '8':
					Temp = "1000";
					break;
				case '9':
					Temp = "1001";
					break;
				case 'A':
					Temp = "1010";
					break;
				case 'B':
					Temp = "1011";
					break;
				case 'C':
					Temp = "1100";
					break;
				case 'D':
					Temp = "1101";
					break;
				case 'E':
					Temp = "1110";
					break;
				case 'F':
					Temp = "1111";
					break;
			}
			BinaryOutput = BinaryOutput + Temp;
		}
		return BinaryOutput;
	}
	
	//
	// Initial permutation.
	//
	public static String InitialPermutation(String BinaryInput)
	{
		String PermutedOutput = "";
		int IP[] = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
				11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
		for (int i = 0; i < 64; i++)
		{
			PermutedOutput = PermutedOutput + Character.toString(BinaryInput.charAt((IP[i] - 1)));
		}
		return PermutedOutput;
	}
	
	//
	// Permutation and Combination 1.
	//
	public static String KeyPC1(String BinaryInput)
	{
		String PermutedOutput = "";
		int PC_1[] = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
				45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
		for (int i = 0; i < 56; i++)
		{
			PermutedOutput = PermutedOutput + Character.toString(BinaryInput.charAt((PC_1[i])));
		}
		return PermutedOutput;
	}
	
	//
	// Reverse permutation.
	//
	public static String ReverseInitialPermutation(String BinaryInput)
	{
		String PermutedOutput = "";
		char TempPermutedOutput[] = new char[64];
		int IP[] = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
				11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
		for (int i = 0; i < 64; i++)
		{
			TempPermutedOutput[IP[i] - 1] = BinaryInput.charAt(i);
			
		}
		for (int i = 0; i < 64; i++)
		{
			PermutedOutput = PermutedOutput + TempPermutedOutput[i];
		}
		
		return PermutedOutput;
	}
	
	//
	// Generate keys.
	//
	public static String GenerateKey(String InputKey, int Flag)
	{
		String NewKey = "";
		
		// Divide key.
		String Left28Bit = "";
		for (int i = 0; i < 28; i++)
		{
			Left28Bit = Left28Bit + InputKey.charAt(i);
		}
		
		String Right28Bit = "";
		for (int i = 0; i < 28; i++)
		{
			Right28Bit = Right28Bit + InputKey.charAt(i + 28);
		}
		
		String NewLeft28Bit = "";
		String NewRight28Bit = "";
		
		// Perform Left circular shift.
		if (Flag == 1 || Flag == 2 || Flag == 9 || Flag == 16)
		{
			char TempLeft0Bit = Left28Bit.charAt(0);
			char TempRight0Bit = Right28Bit.charAt(0);
			
			for (int j = 0; j < 27; j++)
			{
				NewLeft28Bit = NewLeft28Bit + Left28Bit.charAt(j + 1);
			}
			for (int j = 0; j < 27; j++)
			{
				NewRight28Bit = NewRight28Bit + Right28Bit.charAt(j + 1);
			}
			
			NewLeft28Bit = NewLeft28Bit + TempLeft0Bit;
			System.out.println(" Left side Key : " + Left28Bit);
			System.out.println(" After Circular shift : " + NewLeft28Bit);
			
			NewRight28Bit = NewRight28Bit + TempRight0Bit;
			System.out.println(" Right side Key : " + Right28Bit);
			System.out.println(" After Circular shift : " + NewRight28Bit);
		}
		else
		{
			char TempLeft0Bit = Left28Bit.charAt(0);
			char TempLeft1Bit = Left28Bit.charAt(1);
			char TempRight28Bit = Right28Bit.charAt(0);
			char TempRight29Bit = Right28Bit.charAt(1);
			
			for (int j = 0; j < 26; j++)
			{
				NewLeft28Bit = NewLeft28Bit + Left28Bit.charAt(j + 2);
			}
			for (int j = 0; j < 26; j++)
			{
				NewRight28Bit = NewRight28Bit + Right28Bit.charAt(j + 2);
			}
			
			NewLeft28Bit = NewLeft28Bit + TempLeft0Bit;
			NewLeft28Bit = NewLeft28Bit + TempLeft1Bit;
			System.out.println(" Left side Key : " + Left28Bit);
			System.out.println(" After Circular shift : " + NewLeft28Bit);
			
			NewRight28Bit = NewRight28Bit + TempRight28Bit;
			NewRight28Bit = NewRight28Bit + TempRight29Bit;
			System.out.println(" Right side Key : " + Right28Bit);
			System.out.println(" After Circular shift : " + NewRight28Bit);
			
		}
		NewKey = NewLeft28Bit + NewRight28Bit;
		
		return NewKey;
	}
	
	//
	// Permutation and Combination 2.
	//
	public static String KeyPC2(String BinaryInput)
	{
		String PermutedOutput = "";
		int PC_2[] = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36,
				29, 32 };
		for (int i = 0; i < 48; i++)
		{
			PermutedOutput = PermutedOutput + Character.toString(BinaryInput.charAt((PC_2[i] - 1)));
		}
		return PermutedOutput;
	}
}
