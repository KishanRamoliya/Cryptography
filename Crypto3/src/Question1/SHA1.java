// SHA implementation.

package Question1;

import java.util.Scanner;

public class SHA1
{
	
	public static void main(String[] args)
	{
		// Take input from user.
		System.out.println(" Enter the desired Plain Text : ");
		Scanner Sca = new Scanner(System.in);
		String PlainText = Sca.nextLine();
		int PlainTextLength = PlainText.length();
		System.out.println(" Length of Plain Text : " + PlainTextLength);
		String ReverseString = "";
		StringBuilder SB = new StringBuilder();
		// Reverse string.
		for (int i = 0; i < PlainTextLength;)
		{
			int IsSpace = 0;
			int IsSymbol = 0;
			char Symbol = '\0';
			if ((48 <= (int) PlainText.charAt(i) && (int) PlainText.charAt(i) <= 57) || (65 <= (int) PlainText.charAt(i) && (int) PlainText.charAt(i) <= 90)
					|| (97 <= PlainText.charAt(i) && (int) PlainText.charAt(i) <= 122))
			{
				String TempString = "";
				int Flag = 0;
				int TempStringLength = 0;
				for (int j = i; j < PlainTextLength; j++)
				{
					if (PlainText.charAt(j) == ' ')
					{
						IsSpace = 1;
						break;
					}
					else if (!(PlainText.charAt(j) == ' ') && !((48 <= (int) PlainText.charAt(j) && (int) PlainText.charAt(j) <= 57)
							|| (65 <= (int) PlainText.charAt(j) && (int) PlainText.charAt(j) <= 90) || (97 <= PlainText.charAt(j) && (int) PlainText.charAt(j) <= 122)))
					{
						break;
					}
					TempString = TempString + PlainText.charAt(j);
					TempStringLength++;
				}
				if (65 <= (int) TempString.charAt(0) && (int) TempString.charAt(0) <= 90)
				{
					Flag = 1;
				}
				String ReverseTempString = "";
				StringBuilder SBR = new StringBuilder(TempString);
				ReverseTempString = new StringBuilder(TempString).reverse().toString();
				SBR = new StringBuilder(ReverseTempString);
				if (Flag == 1)
				{
					char Zero = ReverseTempString.charAt(0);
					int ZeroNum = (int) Zero - 32;
					Zero = (char) ZeroNum;
					SBR.setCharAt(0, Zero);
					int Max = TempStringLength - 1;
					char MaxChar = ReverseTempString.charAt(Max);
					int MaxCharNum = (int) MaxChar + 32;
					MaxChar = (char) MaxCharNum;
					SBR.setCharAt(Max, MaxChar);
				}
				ReverseString = ReverseString + SBR.toString();
				i = i + TempStringLength - 1;
			}
			else if (!(PlainText.charAt(i) == ' ') && !((48 <= (int) PlainText.charAt(i) && (int) PlainText.charAt(i) <= 57) || (65 <= (int) PlainText.charAt(i) && (int) PlainText.charAt(i) <= 90)
					|| (97 <= PlainText.charAt(i) && (int) PlainText.charAt(i) <= 122)))
			{
				IsSymbol = 1;
				Symbol = PlainText.charAt(i);
				ReverseString = ReverseString + Symbol;
				if (i < PlainTextLength - 1)
				{
					i++;
					if (PlainText.charAt(i) == ' ')
					{
						IsSpace = 1;
					}
					i--;
				}
			}
			else if (IsSymbol == 1 && (PlainText.charAt(i + 1) == ' '))
			{
				i++;
				ReverseString = ReverseString + " ";
			}
			i++;
			if (IsSpace == 1)
			{
				ReverseString = ReverseString + " ";
			}
		}
		System.out.println(" Reverse plain String : " + ReverseString);
		// Convert to binary.
		String BinaryString = "";
		int BinaryStringLenght = 0;
		for (int i = 0; i < ReverseString.length(); i++)
		{
			int ASCIIValue = (int) ReverseString.charAt(i);
			String TempBinary = Integer.toBinaryString(ASCIIValue);
			while (TempBinary.length() < 8)
			{
				TempBinary = "0" + TempBinary;
			}
			BinaryString = BinaryString + TempBinary;
		}
		BinaryStringLenght = BinaryString.length();
		// Padding the string.
		int Calculation = (((447 - BinaryStringLenght) % 512) + 512) % 512;
		String TempString = Integer.toBinaryString(BinaryStringLenght);
		while (TempString.length() < 64)
		{
			TempString = "0" + TempString;
		}
		String Padding = "";
		for (int i = 0; i < Calculation; i++)
		{
			Padding = Padding + 0;
		}
		String FinalBinaryString = BinaryString + "1" + Padding + TempString;
		System.out.println(" Final Binary String length :" + FinalBinaryString.length());
		// Generate sub strings.
		int FinalBinaryStringLength = (FinalBinaryString.length()) / 512;
		String SubStrings[] = new String[512];
		int StartIndex = 0;
		int EndIndex = 512;
		for (int i = 0; i < FinalBinaryStringLength; i++)
		{
			SubStrings[i] = FinalBinaryString.substring(StartIndex, EndIndex);
			StartIndex = EndIndex;
			EndIndex = EndIndex + 512;
		}
		// Initialize H0 to H4 as per the algorithm.
		String H0 = ConvertToBinary("67452301");// initialization oh H1 to H4
		String H1 = ConvertToBinary("EFCDAB89");
		String H2 = ConvertToBinary("98BADCFE");
		String H3 = ConvertToBinary("10325476");
		String H4 = ConvertToBinary("C3D2E1F0");
		String NewString[] = new String[80];
		int TempIndex1 = 0;
		int TempIndex2 = 32;
		for (int i = 0; i < FinalBinaryStringLength; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				NewString[j] = FinalBinaryString.substring(TempIndex1, TempIndex2);
				TempIndex1 = TempIndex2;
				TempIndex2 = TempIndex2 + 32;
			}
			// Perform operations mentioned in the algorithm.
			for (int k = 16; k < 80; k++)
			{
				String TempStore = "";
				for (int l = 0; l < 32; l++)
				{
					int k1 = k - 3;
					String Char1 = Character.toString(NewString[k1].charAt(l));
					int k2 = k - 8;
					String Char2 = Character.toString(NewString[k2].charAt(l));
					int k3 = k - 14;
					String Char3 = Character.toString(NewString[k3].charAt(l));
					int k4 = k - 16;
					String Char4 = Character.toString(NewString[k4].charAt(l));
					String Char5 = "";
					if (Char1.equalsIgnoreCase(Char2))
					{
						Char5 = "0";
					}
					else
					{
						Char5 = "1";
					}
					String Char6 = "";
					if (Char5.equalsIgnoreCase(Char3))
					{
						Char6 = "0";
					}
					else
					{
						Char6 = "1";
					}
					String Char7 = "";
					if (Char6.equalsIgnoreCase(Char4))
					{
						Char7 = "0";
					}
					else
					{
						Char7 = "1";
					}
					TempStore = TempStore + Char7;
					if (l == 31)
					{
						String TempStore1 = "";
						String Temp = Character.toString(TempStore.charAt(0));
						for (int m = 1; m < 32; m++)
						{
							TempStore1 = TempStore1 + Character.toString(TempStore.charAt(m));
						}
						TempStore1 = TempStore1 + Temp;
						NewString[k] = TempStore1;
					}
				}
			}
			String TempH0 = H0;
			String TempH1 = H1;
			String TempH2 = H2;
			String TempH3 = H3;
			String TempH4 = H4;
			for (int k = 0; k < 80; k++)
			{
				String TempString2 = TempH0;
				for (int l = 0; l < 5; l++)
				{
					String TempStore2 = "";
					String Temp = Character.toString(TempH0.charAt(0));
					for (int m = 1; m < 32; m++)
					{
						TempStore2 = TempStore2 + Character.toString(TempH0.charAt(m));
					}
					TempStore2 = TempStore2 + Temp;
					TempH0 = TempStore2;
				}
				long ALong1 = Long.parseLong(TempH0, 2);
				long ALong2 = Long.parseLong(TempH4, 2);
				long ALong3 = Long.parseLong(NewString[k], 2);
				long ALong4 = 0;
				long ALong5 = 0;
				if (k >= 0 && k <= 19)
				{
					ALong4 = Long.parseLong(ConvertToBinary("5A827997"), 2);
					String BString1 = DoAND(TempH1, TempH2);
					String BString2 = DoComplementry(TempH1);
					String BString3 = DoAND(BString2, TempH3);
					String BString4 = DoOR(BString1, BString3);
					ALong5 = Long.parseLong(BString4, 2);
				}
				else if (k >= 20 && k <= 39)
				{
					ALong4 = Long.parseLong(ConvertToBinary("6ED9EBA3"), 2);
					String BString5 = DoEXOR(TempH1, TempH2);
					String BString6 = DoEXOR(BString5, TempH3);
					ALong5 = Long.parseLong(BString6, 2);
					
				}
				else if (k >= 40 && k <= 59)
				{
					ALong4 = Long.parseLong(ConvertToBinary("8F1BBCDC"), 2);
					String BString7 = DoAND(TempH1, TempH2);
					String BString8 = DoAND(TempH1, TempH3);
					String BString9 = DoAND(TempH2, TempH3);
					String BString10 = DoOR(BString7, BString8);
					String BString11 = DoOR(BString10, BString9);
					ALong5 = Long.parseLong(BString11, 2);
					
				}
				else
				{
					ALong4 = Long.parseLong(ConvertToBinary("CA62C1D4"), 2);
					String BString12 = DoEXOR(TempH1, TempH2);
					String BString13 = DoEXOR(BString12, TempH3);
					ALong5 = Long.parseLong(BString13, 2);
				}
				long ALongCombine = (ALong1 + ALong5 + ALong2 + ALong4 + ALong3) % ((long) Math.pow(2, 32));
				String TempString3 = Long.toBinaryString(ALongCombine);
				if (TempString3.length() != 32)
				{
					while (TempString3.length() != 32)
					{
						TempString3 = "0" + TempString3;
					}
				}
				TempH4 = TempH3;
				TempH3 = TempH2;
				for (int l = 0; l < 30; l++)
				{
					String TempStore2 = "";
					String temp = Character.toString(TempH1.charAt(0));
					for (int m = 1; m < 32; m++)
					{
						TempStore2 = TempStore2 + Character.toString(TempH1.charAt(m));
					}
					TempStore2 = TempStore2 + temp;
					TempH1 = TempStore2;
				}
				TempH2 = TempH1;
				TempH1 = TempString2;
				TempH0 = TempString3;
			}
			long HLongBString0 = Long.parseLong(H0, 2);
			long ALongBString = Long.parseLong(TempH0, 2);
			long HLongBString1 = Long.parseLong(H1, 2);
			long BStringBString = Long.parseLong(TempH1, 2);
			long HLongBString2 = Long.parseLong(H2, 2);
			long CBString = Long.parseLong(TempH2, 2);
			long HLongBString3 = Long.parseLong(H3, 2);
			long DBString = Long.parseLong(TempH3, 2);
			long HLongBString4 = Long.parseLong(H4, 2);
			long EBString = Long.parseLong(TempH4, 2);
			long HLong0l = (HLongBString0 + ALongBString) % ((long) Math.pow(2, 32));// addition
			long Hlong1l = (HLongBString1 + BStringBString) % ((long) Math.pow(2, 32));
			long HLong2l = (HLongBString2 + CBString) % ((long) Math.pow(2, 32));
			long HLong3l = (HLongBString3 + DBString) % ((long) Math.pow(2, 32));
			long HLong4l = (HLongBString4 + EBString) % ((long) Math.pow(2, 32));
			H0 = Long.toBinaryString(HLong0l);
			while (H0.length() != 32)
			{
				H0 = "0" + H0;
			}
			H1 = Long.toBinaryString(Hlong1l);
			while (H1.length() != 32)
			{
				H1 = "0" + H1;
			}
			H2 = Long.toBinaryString(HLong2l);
			while (H2.length() != 32)
			{
				H2 = "0" + H2;
			}
			H3 = Long.toBinaryString(HLong3l);
			while (H3.length() != 32)
			{
				H3 = "0" + H3;
			}
			H4 = Long.toBinaryString(HLong4l);
			while (H4.length() != 32)
			{
				H4 = "0" + H4;
			}
		}
		String HashFunctionCode = H0 + H1 + H2 + H3 + H4;
		System.out.println(" Hash function in binary : " + HashFunctionCode);
		System.out.println(" Hash function in binary's length : " + HashFunctionCode.length());
		String FinalAnswer = "";
		int Tempj = 0;
		int Tempk = 4;
		// Convert Binary to HEX.
		for (int i = 0; i < 40; i++)
		{
			String SubString = HashFunctionCode.substring(Tempj, Tempk);
			int Tempbi = Integer.parseInt(SubString, 2);
			FinalAnswer = FinalAnswer + Integer.toHexString(Tempbi);
			Tempj = Tempk;
			Tempk = Tempk + 4;
		}
		// Final output.
		System.out.println(" Final Output : " + FinalAnswer);
	}
	
	// Convert to Binary.
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
	
	// AND.
	public static String DoAND(String First, String Second)
	{
		String Char3 = "";
		String Char4 = "";
		for (int i = 0; i < 32; i++)
		{
			String Char1 = Character.toString(First.charAt(i));
			String Char2 = Character.toString(Second.charAt(i));
			
			if (Char1.equalsIgnoreCase("1") && Char2.equalsIgnoreCase("1"))
			{
				Char3 = "1";
			}
			else
			{
				Char3 = "0";
			}
			Char4 = Char4 + Char3;
		}
		return Char4;
	}
	
	// OR.
	public static String DoOR(String First, String Second)
	{
		String Char3 = "";
		String Char4 = "";
		for (int i = 0; i < 32; i++)
		{
			String Char1 = Character.toString(First.charAt(i));
			String Char2 = Character.toString(Second.charAt(i));
			
			if (Char1.equalsIgnoreCase("1") || Char2.equalsIgnoreCase("1"))
			{
				Char3 = "1";
			}
			else
			{
				Char3 = "0";
			}
			Char4 = Char4 + Char3;
		}
		return Char4;
	}
	
	// Compliment.
	public static String DoComplementry(String First)
	{
		String Char2 = "";
		for (int i = 0; i < 32; i++)
		{
			String Char1 = Character.toString(First.charAt(i));
			String Char3 = "";
			if (Char1.equalsIgnoreCase("1"))
			{
				Char3 = "0";
			}
			else
			{
				Char3 = "1";
			}
			Char2 = Char2 + Char3;
		}
		return Char2;
	}
	
	// EX-OR.
	public static String DoEXOR(String First, String Second)
	{
		String Char3 = "";
		String Char4 = "";
		for (int i = 0; i < 32; i++)
		{
			String Char1 = Character.toString(First.charAt(i));
			String Char2 = Character.toString(Second.charAt(i));
			
			if (Char1.equalsIgnoreCase(Char2))
			{
				Char3 = "0";
			}
			else
			{
				Char3 = "1";
			}
			Char4 = Char4 + Char3;
		}
		return Char4;
	}
	
}
