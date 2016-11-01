// PGM implementation.

package Question2;

import java.util.Scanner;

public class PGM
{
	public static void main(String args[])
	{
		System.out.println(" PGM. ");
		System.out.println();
		
		int Keys[] = KeyStream();
		System.out.println(" Key stream generated. ");
		for (int i = 0; i < 10; i++)
		{
			System.out.println(" Key [ " + i + " ] : " + Keys[i]);
		}
		System.out.println();
		
		Scanner ScanText = new Scanner(System.in);
		
		String[] Matrix = { " ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?", "@", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "\\", "]", "^", "_", "`", "a", "b", "c", "d", "e",
				"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", " ", " ", "/r", "/n", " " };
				
		int M = 95 * 95 * 95;
		
		Boolean Choice = false;
		
		String FinalPlainText = "";
		
		while (!Choice)
		{
			System.out.println(" Enter the Cipher text : ");
			String CipherText = ScanText.nextLine();
			
			String SubString[] = new String[10];
			
			int Temp1 = 0;
			int Temp2 = 3;
			for (int i = 0; i < 10; i++)
			{
				SubString[i] = CipherText.substring(Temp1, Temp2);
				Temp1 = Temp1 + 3;
				Temp2 = Temp2 + 3;
			}
			
			int Index1[] = new int[10];
			int Index2[] = new int[10];
			int Index3[] = new int[10];
			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 95; j++)
				{
					if (Character.toString(SubString[i].charAt(0)).equals(Matrix[j]))
					{
						Index1[i] = j;
					}
					if (Character.toString(SubString[i].charAt(1)).equals(Matrix[j]))
					{
						Index2[i] = j;
					}
					if (Character.toString(SubString[i].charAt(2)).equals(Matrix[j]))
					{
						Index3[i] = j;
					}
				}
			}
			
			int IndexX[] = new int[10];
			int IndexY[] = new int[10];
			for (int i = 0; i < 10; i++)
			{
				IndexY[i] = (Index1[i] * 95 * 95) + (Index2[i] * 95) + (Index3[i]);
				if (IndexY[i] > Keys[i])
				{
					IndexX[i] = (IndexY[i] - Keys[i]) % M;
				}
				else
				{
					IndexX[i] = (Keys[i] - IndexY[i]) % M;
				}
			}
			
			String DecrypetedText[] = new String[10];
			for (int i = 0; i < 10; i++)
			{
				DecrypetedText[i] = Integer.toString(IndexX[i]);
				while (DecrypetedText[i].length() < 6)
				{
					DecrypetedText[i] = 0 + DecrypetedText[i];
				}
			}
			
			for (int i = 0; i < 10; i++)
			{
				int RowNumber[] = new int[64];
				int ColumnNumber[] = new int[64];
				
				for (int j = 0; j < DecrypetedText[i].length(); j = j + 2)
				{
					RowNumber[i] = Integer.parseInt(Character.toString(DecrypetedText[i].charAt(j)));
				}
				
				for (int k = 0; k < DecrypetedText[i].length(); k = k + 2)
				{
					ColumnNumber[k] = Integer.parseInt(Character.toString(DecrypetedText[i].charAt(k + 1)));
				}
				
				String PlainText = "";
				
				for (int l = 0; l < DecrypetedText[i].length(); l = l + 2)
				{
					if (ColumnNumber[l] == 0)
					{
						PlainText = PlainText + Matrix[ColumnNumber[l]];
					}
					else if (ColumnNumber[l] == 9)
					{
						PlainText = PlainText + Matrix[(((RowNumber[l])) * 10) + ColumnNumber[l]];
					}
					else if (RowNumber[l] == 0)
					{
						PlainText = PlainText + Matrix[ColumnNumber[l]];
					}
					else
					{
						PlainText = PlainText + Matrix[(RowNumber[l] * 10) + (ColumnNumber[l])];
					}
				}
				
				FinalPlainText = FinalPlainText + PlainText;
			}
			System.out.println(" Enter Yes to continue and No to finish the decryption. ");
			Scanner Sca1 = new Scanner(System.in);
			System.out.println(" Enter Yes or No : ");
			String TempChoice = Sca1.nextLine();
			Choice = TempChoice.equals("No");
			if (Choice)
			{
				break;
			}
		}
		System.out.println(" Final Plain text : " + FinalPlainText);
	}
	
	public static int[] KeyStream()
	{
		// Alpha.
		int Row1Alpha0[] = { 6, 9, 10, 7, 5, 3, 2, 1, 8, 4 };
		int Row1Alpha1[] = { 2, 5, 9, 3, 10, 4, 1, 7, 6, 8 };
		int Row1Alpha2[] = { 1, 6, 7, 5, 4, 9, 10, 2, 8, 3 };
		int Row1Alpha3[] = { 10, 5, 1, 6, 7, 8, 9, 2, 4, 3 };
		int Row1Alpha4[] = { 5, 10, 4, 9, 8, 2, 7, 3, 6, 1 };
		int Row1Alpha5[] = { 4, 9, 10, 6, 3, 2, 7, 8, 1, 5 };
		int Row1Alpha6[] = { 3, 8, 6, 2, 5, 4, 10, 1, 9, 7 };
		int Row1Alpha7[] = { 7, 10, 3, 1, 5, 2, 6, 9, 8, 4 };
		int Row1Alpha8[] = { 8, 10, 6, 3, 5, 2, 7, 9, 1, 4 };
		int Row1Alpha9[] = { 9, 3, 2, 6, 7, 10, 4, 8, 1, 5 };
		int Row2Alpha0[] = { 1, 2, 10, 3, 7, 4, 6, 8, 5, 9 };
		int Row2Alpha1[] = { 1, 5, 9, 4, 6, 2, 8, 3, 10, 7 };
		int Row2Alpha2[] = { 1, 6, 7, 4, 9, 8, 3, 2, 5, 10 };
		int Row2Alpha3[] = { 1, 8, 5, 6, 7, 2, 9, 4, 3, 10 };
		int Row2Alpha4[] = { 1, 9, 4, 2, 5, 7, 6, 10, 8, 3 };
		int Row2Alpha5[] = { 1, 3, 7, 6, 4, 10, 5, 8, 9, 2 };
		int Row2Alpha6[] = { 1, 7, 10, 6, 5, 2, 8, 9, 4, 3 };
		int Row2Alpha7[] = { 1, 10, 2, 4, 7, 3, 6, 5, 9, 8 };
		int Row2Alpha8[] = { 1, 4, 6, 5, 9, 10, 2, 7, 3, 8 };
		int Row3Alpha0[] = { 1, 2, 9, 8, 5, 4, 7, 6, 10, 3 };
		int Row3Alpha1[] = { 1, 2, 8, 10, 6, 7, 5, 9, 4, 3 };
		int Row3Alpha2[] = { 1, 2, 6, 3, 7, 4, 9, 8, 5, 10 };
		int Row3Alpha3[] = { 1, 2, 5, 10, 3, 7, 4, 8, 9, 6 };
		int Row3Alpha4[] = { 1, 2, 7, 4, 6, 8, 9, 5, 3, 10 };
		int Row3Alpha5[] = { 1, 2, 3, 5, 9, 8, 10, 7, 6, 4 };
		int Row3Alpha6[] = { 1, 2, 4, 10, 7, 6, 5, 9, 8, 3 };
		int Row3Alpha7[] = { 1, 2, 10, 9, 3, 6, 4, 8, 7, 5 };
		int Row4Alpha0[] = { 1, 2, 3, 5, 7, 8, 10, 6, 9, 4 };
		int Row4Alpha1[] = { 1, 2, 3, 4, 5, 8, 9, 7, 10, 6 };
		int Row4Alpha2[] = { 1, 2, 3, 8, 4, 5, 7, 6, 10, 9 };
		int Row4Alpha3[] = { 1, 2, 3, 10, 4, 7, 6, 5, 9, 8 };
		int Row4Alpha4[] = { 1, 2, 3, 9, 6, 10, 5, 4, 7, 8 };
		int Row4Alpha5[] = { 1, 2, 3, 6, 9, 5, 10, 4, 7, 8 };
		int Row4Alpha6[] = { 1, 2, 3, 7, 8, 10, 6, 4, 5, 9 };
		int Row5Alpha0[] = { 1, 2, 3, 4, 10, 8, 5, 7, 9, 6 };
		int Row5Alpha1[] = { 1, 2, 3, 4, 5, 9, 7, 6, 8, 10 };
		int Row5Alpha2[] = { 1, 2, 3, 4, 8, 5, 6, 10, 9, 7 };
		int Row5Alpha3[] = { 1, 2, 3, 4, 7, 6, 8, 5, 9, 10 };
		int Row5Alpha4[] = { 1, 2, 3, 4, 6, 5, 10, 7, 8, 9 };
		int Row5Alpha5[] = { 1, 2, 3, 4, 9, 6, 10, 8, 5, 7 };
		int Row6Alpha0[] = { 1, 2, 3, 4, 5, 8, 10, 6, 9, 7 };
		int Row6Alpha1[] = { 1, 2, 3, 4, 5, 7, 8, 6, 9, 10 };
		int Row6Alpha2[] = { 1, 2, 3, 4, 5, 6, 7, 10, 8, 9 };
		int Row6Alpha3[] = { 1, 2, 3, 4, 5, 10, 9, 8, 7, 6 };
		int Row6Alpha4[] = { 1, 2, 3, 4, 5, 9, 7, 10, 6, 8 };
		int Row7Alpha0[] = { 1, 2, 3, 4, 5, 6, 10, 7, 9, 8 };
		int Row7Alpha1[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int Row7Alpha2[] = { 1, 2, 3, 4, 5, 6, 8, 10, 9, 7 };
		int Row7Alpha3[] = { 1, 2, 3, 4, 5, 6, 9, 8, 10, 7 };
		int Row8Alpha0[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int Row8Alpha1[] = { 1, 2, 3, 4, 5, 6, 7, 10, 8, 9 };
		int Row8Alpha2[] = { 1, 2, 3, 4, 5, 6, 7, 9, 10, 8 };
		
		// Beta.
		int Row1Beta0[] = { 2, 8, 7, 3, 4, 1, 6, 5, 10, 9 };
		int Row1Beta1[] = { 10, 8, 7, 6, 1, 4, 9, 2, 3, 5 };
		int Row1Beta2[] = { 7, 3, 6, 5, 4, 1, 10, 2, 8, 9 };
		int Row1Beta3[] = { 9, 10, 1, 7, 6, 8, 4, 3, 2, 5 };
		int Row1Beta4[] = { 6, 5, 10, 1, 8, 3, 4, 7, 9, 2 };
		int Row1Beta5[] = { 8, 7, 2, 5, 6, 3, 9, 10, 4, 1 };
		int Row1Beta6[] = { 5, 4, 7, 6, 1, 10, 3, 8, 2, 9 };
		int Row1Beta7[] = { 1, 2, 9, 10, 7, 6, 3, 5, 4, 8 };
		int Row1Beta8[] = { 4, 6, 3, 9, 10, 5, 7, 2, 1, 8 };
		int Row1Beta9[] = { 3, 7, 10, 4, 1, 9, 8, 6, 5, 2 };
		int Row2Beta0[] = { 1, 6, 4, 7, 9, 8, 10, 2, 5, 3 };
		int Row2Beta1[] = { 1, 3, 8, 7, 10, 5, 9, 4, 6, 2 };
		int Row2Beta2[] = { 1, 5, 3, 10, 9, 2, 7, 4, 8, 6 };
		int Row2Beta3[] = { 1, 8, 5, 7, 9, 6, 10, 4, 3, 2 };
		int Row2Beta4[] = { 1, 2, 5, 9, 4, 10, 7, 6, 8, 3 };
		int Row2Beta5[] = { 1, 10, 4, 9, 7, 8, 5, 6, 2, 3 };
		int Row2Beta6[] = { 1, 4, 7, 10, 9, 6, 5, 3, 8, 2 };
		int Row2Beta7[] = { 1, 9, 4, 5, 8, 3, 6, 10, 7, 2 };
		int Row2Beta8[] = { 1, 7, 8, 2, 4, 6, 3, 10, 9, 5 };
		int Row3Beta0[] = { 1, 2, 5, 3, 8, 4, 6, 7, 10, 9 };
		int Row3Beta1[] = { 1, 2, 9, 6, 5, 7, 4, 3, 8, 10 };
		int Row3Beta2[] = { 1, 2, 7, 6, 10, 9, 8, 5, 4, 3 };
		int Row3Beta3[] = { 1, 2, 10, 8, 7, 5, 9, 3, 6, 4 };
		int Row3Beta4[] = { 1, 2, 6, 5, 8, 3, 7, 9, 4, 10 };
		int Row3Beta5[] = { 1, 2, 4, 7, 8, 3, 6, 9, 10, 5 };
		int Row3Beta6[] = { 1, 2, 3, 10, 9, 5, 7, 8, 4, 6 };
		int Row3Beta7[] = { 1, 2, 8, 9, 5, 7, 6, 10, 4, 3 };
		int Row4Beta0[] = { 1, 2, 3, 6, 5, 4, 8, 9, 10, 7 };
		int Row4Beta1[] = { 1, 2, 3, 4, 9, 8, 6, 7, 10, 5 };
		int Row4Beta2[] = { 1, 2, 3, 7, 9, 6, 4, 8, 5, 10 };
		int Row4Beta3[] = { 1, 2, 3, 5, 9, 8, 6, 7, 4, 10 };
		int Row4Beta4[] = { 1, 2, 3, 8, 7, 4, 5, 10, 9, 6 };
		int Row4Beta5[] = { 1, 2, 3, 9, 4, 6, 7, 8, 5, 10 };
		int Row4Beta6[] = { 1, 2, 3, 10, 4, 7, 9, 8, 6, 5 };
		int Row5Beta0[] = { 1, 2, 3, 4, 9, 6, 8, 7, 5, 10 };
		int Row5Beta1[] = { 1, 2, 3, 4, 5, 10, 8, 6, 7, 9 };
		int Row5Beta2[] = { 1, 2, 3, 4, 8, 10, 7, 5, 9, 6 };
		int Row5Beta3[] = { 1, 2, 3, 4, 6, 7, 8, 10, 9, 5 };
		int Row5Beta4[] = { 1, 2, 3, 4, 10, 5, 7, 9, 6, 8 };
		int Row5Beta5[] = { 1, 2, 3, 4, 7, 6, 8, 10, 5, 9 };
		int Row6Beta0[] = { 1, 2, 3, 4, 5, 9, 8, 6, 10, 7 };
		int Row6Beta1[] = { 1, 2, 3, 4, 5, 6, 8, 10, 9, 7 };
		int Row6Beta2[] = { 1, 2, 3, 4, 5, 8, 10, 6, 9, 7 };
		int Row6Beta3[] = { 1, 2, 3, 4, 5, 7, 8, 9, 10, 6 };
		int Row6Beta4[] = { 1, 2, 3, 4, 5, 10, 7, 9, 8, 6 };
		int Row7Beta0[] = { 1, 2, 3, 4, 5, 6, 9, 7, 8, 10 };
		int Row7Beta1[] = { 1, 2, 3, 4, 5, 6, 10, 7, 9, 8 };
		int Row7Beta2[] = { 1, 2, 3, 4, 5, 6, 8, 9, 7, 10 };
		int Row7Beta3[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int Row8Beta0[] = { 1, 2, 3, 4, 5, 6, 7, 10, 8, 9 };
		int Row8Beta1[] = { 1, 2, 3, 4, 5, 6, 7, 9, 10, 8 };
		int Row8Beta2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		int R[] = { 10, 9, 8, 7, 6, 5, 4, 3 };
		
		int M[] = new int[10];
		M[0] = 1;
		for (int i = 1; i < 8; i++)
		{
			M[i] = 1;
			for (int j = 0; j < i; j++)
			{
				M[i] = M[i] * R[j];
			}
		}
		
		int Row1PM[] = new int[10];
		for (int i = 0; i < 10; i++)
		{
			Row1PM[i] = i * M[0];
		}
		
		int Row2PM[] = new int[9];
		for (int i = 0; i < 9; i++)
		{
			Row2PM[i] = i * M[1];
		}
		
		int Row3PM[] = new int[8];
		for (int i = 0; i < 8; i++)
		{
			Row3PM[i] = i * M[2];
		}
		
		int Row4PM[] = new int[7];
		for (int i = 0; i < 7; i++)
		{
			Row4PM[i] = i * M[3];
		}
		
		int Row5PM[] = new int[6];
		for (int i = 0; i < 6; i++)
		{
			Row5PM[i] = i * M[4];
		}
		
		int Row6PM[] = new int[5];
		for (int i = 0; i < 5; i++)
		{
			Row6PM[i] = i * M[5];
		}
		
		int Row7PM[] = new int[4];
		for (int i = 0; i < 4; i++)
		{
			Row7PM[i] = i * M[6];
		}
		
		int Row8PM[] = new int[3];
		for (int i = 0; i < 3; i++)
		{
			Row8PM[i] = i * M[7];
		}
		
		int KeyStream[] = new int[10];
		
		for (int ik = 2000; ik < 2010; ik++)
		{
			int Row1 = 0;
			int Row2 = 0;
			int Row3 = 0;
			int Row4 = 0;
			int Row5 = 0;
			int Row6 = 0;
			int Row7 = 0;
			int Row8 = 0;
			
			for (int a = 0; a < 10; a++)
			{
				for (int b = 0; b < 9; b++)
				{
					for (int c = 0; c < 8; c++)
					{
						for (int d = 0; d < 7; d++)
						{
							for (int e = 0; e < 6; e++)
							{
								for (int f = 0; f < 5; f++)
								{
									for (int g = 0; g < 4; g++)
									{
										for (int h = 0; h < 3; h++)
										{
											int sum = Row1PM[a] + Row2PM[b] + Row3PM[c] + Row4PM[d] + Row5PM[e] + Row6PM[f] + Row7PM[g] + Row8PM[h];
											if (sum == ik)
											{
												Row1 = a;
												Row2 = b;
												Row3 = c;
												Row4 = d;
												Row5 = e;
												Row6 = f;
												Row7 = g;
												Row8 = h;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			int Q1[] = new int[10];
			int Q2[] = new int[10];
			int Q3[] = new int[10];
			int Q4[] = new int[10];
			int Q5[] = new int[10];
			int Q6[] = new int[10];
			int Q7[] = new int[10];
			int Q8[] = new int[10];
			switch (Row1)
			{
				case 0:
					System.arraycopy(Row1Alpha0, 0, Q1, 0, 10);
					break;
				case 1:
					System.arraycopy(Row1Alpha1, 0, Q1, 0, 10);
					break;
				case 2:
					System.arraycopy(Row1Alpha2, 0, Q1, 0, 10);
					break;
				case 3:
					System.arraycopy(Row1Alpha3, 0, Q1, 0, 10);
					break;
				case 4:
					System.arraycopy(Row1Alpha4, 0, Q1, 0, 10);
					break;
				case 5:
					System.arraycopy(Row1Alpha5, 0, Q1, 0, 10);
					break;
				case 6:
					System.arraycopy(Row1Alpha6, 0, Q1, 0, 10);
					break;
				case 7:
					System.arraycopy(Row1Alpha7, 0, Q1, 0, 10);
					break;
				case 8:
					System.arraycopy(Row1Alpha8, 0, Q1, 0, 10);
					break;
				case 9:
					System.arraycopy(Row1Alpha9, 0, Q1, 0, 10);
					break;
			}
			switch (Row2)
			{
				case 0:
					System.arraycopy(Row2Alpha0, 0, Q2, 0, 10);
					break;
				case 1:
					System.arraycopy(Row2Alpha1, 0, Q2, 0, 10);
					break;
				case 2:
					System.arraycopy(Row2Alpha2, 0, Q2, 0, 10);
					break;
				case 3:
					System.arraycopy(Row2Alpha3, 0, Q2, 0, 10);
					break;
				case 4:
					System.arraycopy(Row2Alpha4, 0, Q2, 0, 10);
					break;
				case 5:
					System.arraycopy(Row2Alpha5, 0, Q2, 0, 10);
					break;
				case 6:
					System.arraycopy(Row2Alpha6, 0, Q2, 0, 10);
					break;
				case 7:
					System.arraycopy(Row2Alpha7, 0, Q2, 0, 10);
					break;
				case 8:
					System.arraycopy(Row2Alpha8, 0, Q2, 0, 10);
					break;
			}
			switch (Row3)
			{
				case 0:
					System.arraycopy(Row3Alpha0, 0, Q3, 0, 10);
					break;
				case 1:
					System.arraycopy(Row3Alpha1, 0, Q3, 0, 10);
					break;
				case 2:
					System.arraycopy(Row3Alpha2, 0, Q3, 0, 10);
					break;
				case 3:
					System.arraycopy(Row3Alpha3, 0, Q3, 0, 10);
					break;
				case 4:
					System.arraycopy(Row3Alpha4, 0, Q3, 0, 10);
					break;
				case 5:
					System.arraycopy(Row3Alpha5, 0, Q3, 0, 10);
					break;
				case 6:
					System.arraycopy(Row3Alpha6, 0, Q3, 0, 10);
					break;
				case 7:
					System.arraycopy(Row3Alpha7, 0, Q3, 0, 10);
					break;
			}
			switch (Row4)
			{
				case 0:
					System.arraycopy(Row4Alpha0, 0, Q4, 0, 10);
					break;
				case 1:
					System.arraycopy(Row4Alpha1, 0, Q4, 0, 10);
					break;
				case 2:
					System.arraycopy(Row4Alpha2, 0, Q4, 0, 10);
					break;
				case 3:
					System.arraycopy(Row4Alpha3, 0, Q4, 0, 10);
					break;
				case 4:
					System.arraycopy(Row4Alpha4, 0, Q4, 0, 10);
					break;
				case 5:
					System.arraycopy(Row4Alpha5, 0, Q4, 0, 10);
					break;
				case 6:
					System.arraycopy(Row4Alpha6, 0, Q4, 0, 10);
					break;
			}
			switch (Row5)
			{
				case 0:
					System.arraycopy(Row5Alpha0, 0, Q5, 0, 10);
					break;
				case 1:
					System.arraycopy(Row5Alpha1, 0, Q5, 0, 10);
					break;
				case 2:
					System.arraycopy(Row5Alpha2, 0, Q5, 0, 10);
					break;
				case 3:
					System.arraycopy(Row5Alpha3, 0, Q5, 0, 10);
					break;
				case 4:
					System.arraycopy(Row5Alpha4, 0, Q5, 0, 10);
					break;
				case 5:
					System.arraycopy(Row5Alpha5, 0, Q5, 0, 10);
					break;
			}
			switch (Row6)
			{
				case 0:
					System.arraycopy(Row6Alpha0, 0, Q6, 0, 10);
					break;
				case 1:
					System.arraycopy(Row6Alpha1, 0, Q6, 0, 10);
					break;
				case 2:
					System.arraycopy(Row6Alpha2, 0, Q6, 0, 10);
					break;
				case 3:
					System.arraycopy(Row6Alpha3, 0, Q6, 0, 10);
					break;
				case 4:
					System.arraycopy(Row6Alpha4, 0, Q6, 0, 10);
					break;
			}
			switch (Row7)
			{
				case 0:
					System.arraycopy(Row7Alpha0, 0, Q7, 0, 10);
					break;
				case 1:
					System.arraycopy(Row7Alpha1, 0, Q7, 0, 10);
					break;
				case 2:
					System.arraycopy(Row7Alpha2, 0, Q7, 0, 10);
					break;
				case 3:
					System.arraycopy(Row7Alpha3, 0, Q7, 0, 10);
					break;
			}
			switch (Row8)
			{
				case 0:
					System.arraycopy(Row8Alpha0, 0, Q8, 0, 10);
					break;
				case 1:
					System.arraycopy(Row8Alpha1, 0, Q8, 0, 10);
					break;
				case 2:
					System.arraycopy(Row8Alpha2, 0, Q8, 0, 10);
					break;
			}
			
			PGM PGMObject = new PGM();
			
			int OutputArray[] = PGMObject.FunctionComposit(Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8);
			
			int Temp = 0;
			int Addition1 = 0;
			if (OutputArray[0] == Row1Beta0[0])
			{
				Temp = 0;
				Addition1 = Row1PM[0];
			}
			if (OutputArray[0] == Row1Beta1[0])
			{
				Temp = 1;
				Addition1 = Row1PM[1];
			}
			if (OutputArray[0] == Row1Beta2[0])
			{
				Temp = 2;
				Addition1 = Row1PM[2];
			}
			if (OutputArray[0] == Row1Beta3[0])
			{
				Temp = 3;
				Addition1 = Row1PM[3];
			}
			if (OutputArray[0] == Row1Beta4[0])
			{
				Temp = 4;
				Addition1 = Row1PM[4];
			}
			if (OutputArray[0] == Row1Beta5[0])
			{
				Temp = 5;
				Addition1 = Row1PM[5];
			}
			if (OutputArray[0] == Row1Beta6[0])
			{
				Temp = 6;
				Addition1 = Row1PM[6];
			}
			if (OutputArray[0] == Row1Beta7[0])
			{
				Temp = 7;
				Addition1 = Row1PM[7];
			}
			if (OutputArray[0] == Row1Beta8[0])
			{
				Temp = 8;
				Addition1 = Row1PM[8];
			}
			
			int InverseBeta[] = new int[10];
			int OutputArray1[] = new int[10];
			switch (Temp)
			{
				case 0:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta0);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);// finding
					break;
				case 1:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta1);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 2:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta2);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 3:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta3);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 4:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta4);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 5:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta5);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);// finding
					break;
				case 6:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta6);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 7:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta7);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 8:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta8);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
				case 9:
					InverseBeta = PGMObject.FunctionInverse(Row1Beta9);
					OutputArray1 = PGMObject.FunctionComposit2(OutputArray, InverseBeta);
					break;
			}
			
			int Temp1 = 0;
			int Addition2 = 0;
			if (OutputArray1[1] == Row2Beta0[1])
			{
				Temp1 = 0;
				Addition2 = Row2PM[0];
			}
			if (OutputArray1[1] == Row2Beta1[1])
			{
				Temp1 = 1;
				Addition2 = Row2PM[1];
			}
			if (OutputArray1[1] == Row2Beta2[1])
			{
				Temp1 = 2;
				Addition2 = Row2PM[2];
			}
			if (OutputArray1[1] == Row2Beta3[1])
			{
				Temp1 = 3;
				Addition2 = Row2PM[3];
			}
			if (OutputArray1[1] == Row2Beta4[1])
			{
				Temp1 = 4;
				Addition2 = Row2PM[4];
			}
			if (OutputArray1[1] == Row2Beta5[1])
			{
				Temp1 = 5;
				Addition2 = Row2PM[5];
			}
			if (OutputArray1[1] == Row2Beta6[1])
			{
				Temp1 = 6;
				Addition2 = Row2PM[6];
			}
			if (OutputArray1[1] == Row2Beta7[1])
			{
				Temp1 = 7;
				Addition2 = Row2PM[7];
			}
			if (OutputArray1[1] == Row2Beta8[1])
			{
				Temp1 = 8;
				Addition2 = Row2PM[8];
			}
			
			int InverseBeta1[] = new int[10];
			int OutputArray2[] = new int[10];
			switch (Temp1)
			{
				case 0:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta0);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 1:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta1);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 2:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta2);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 3:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta3);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 4:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta4);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 5:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta5);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 6:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta6);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 7:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta7);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
				case 8:
					InverseBeta1 = PGMObject.FunctionInverse(Row2Beta8);
					OutputArray2 = PGMObject.FunctionComposit2(OutputArray1, InverseBeta1);
					break;
					
			}
			
			int Temp2 = 0;
			int Addition3 = 0;
			if (OutputArray2[2] == Row3Beta0[2])
			{
				Temp2 = 0;
				Addition3 = Row3PM[0];
			}
			if (OutputArray2[2] == Row3Beta1[2])
			{
				Temp2 = 1;
				Addition3 = Row3PM[1];
			}
			if (OutputArray2[2] == Row3Beta2[2])
			{
				Temp2 = 2;
				Addition3 = Row3PM[2];
			}
			if (OutputArray2[2] == Row3Beta3[2])
			{
				Temp2 = 3;
				Addition3 = Row3PM[3];
			}
			if (OutputArray2[2] == Row3Beta4[2])
			{
				Temp2 = 4;
				Addition3 = Row3PM[4];
			}
			if (OutputArray2[2] == Row3Beta5[2])
			{
				Temp2 = 5;
				Addition3 = Row3PM[5];
			}
			if (OutputArray2[2] == Row3Beta6[2])
			{
				Temp2 = 6;
				Addition3 = Row3PM[6];
			}
			if (OutputArray2[2] == Row3Beta7[2])
			{
				Temp2 = 7;
				Addition3 = Row3PM[7];
			}
			
			int InverseBeta2[] = new int[10];
			int OutputArray3[] = new int[10];
			switch (Temp2)
			{
				case 0:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta0);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 1:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta1);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 2:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta2);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 3:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta3);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 4:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta4);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 5:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta5);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 6:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta6);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
				case 7:
					InverseBeta2 = PGMObject.FunctionInverse(Row3Beta7);
					OutputArray3 = PGMObject.FunctionComposit2(OutputArray2, InverseBeta2);
					break;
					
			}
			
			int Temp3 = 0;
			int Addition4 = 0;
			if (OutputArray3[3] == Row4Beta0[3])
			{
				Temp3 = 0;
				Addition4 = Row4PM[0];
			}
			if (OutputArray3[3] == Row4Beta1[3])
			{
				Temp3 = 1;
				Addition4 = Row4PM[1];
			}
			if (OutputArray3[3] == Row4Beta2[3])
			{
				Temp3 = 2;
				Addition4 = Row4PM[2];
			}
			if (OutputArray3[3] == Row4Beta3[3])
			{
				Temp3 = 3;
				Addition4 = Row4PM[3];
			}
			if (OutputArray3[3] == Row4Beta4[3])
			{
				Temp3 = 4;
				Addition4 = Row4PM[4];
			}
			if (OutputArray3[3] == Row4Beta5[3])
			{
				Temp3 = 5;
				Addition4 = Row4PM[5];
			}
			if (OutputArray3[3] == Row4Beta6[3])
			{
				Temp3 = 6;
				Addition4 = Row4PM[6];
			}
			
			int InverseBeta4[] = new int[10];
			int OutputArray4[] = new int[10];
			switch (Temp3)
			{
				case 0:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta0);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					break;
				case 1:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta1);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					break;
				case 2:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta2);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					break;
				case 3:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta3);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					break;
				case 4:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta4);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					break;
				case 5:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta5);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					break;
				case 6:
					InverseBeta4 = PGMObject.FunctionInverse(Row4Beta6);
					OutputArray4 = PGMObject.FunctionComposit2(OutputArray3, InverseBeta4);
					
			}
			
			int Temp4 = 0;
			int Addition5 = 0;
			if (OutputArray4[4] == Row5Beta0[4])
			{
				Temp4 = 0;
				Addition5 = Row5PM[0];
			}
			if (OutputArray4[4] == Row5Beta1[4])
			{
				Temp4 = 1;
				Addition5 = Row5PM[1];
			}
			if (OutputArray4[4] == Row5Beta2[4])
			{
				Temp4 = 2;
				Addition5 = Row5PM[2];
			}
			if (OutputArray4[4] == Row5Beta3[4])
			{
				Temp4 = 3;
				Addition5 = Row5PM[3];
			}
			if (OutputArray4[4] == Row5Beta4[4])
			{
				Temp4 = 4;
				Addition5 = Row5PM[4];
			}
			if (OutputArray4[4] == Row5Beta5[4])
			{
				Temp4 = 5;
				Addition5 = Row5PM[5];
			}
			
			int InverseBeta5[] = new int[10];
			int OutputArray5[] = new int[10];
			switch (Temp4)
			{
				case 0:
					InverseBeta5 = PGMObject.FunctionInverse(Row5Beta0);
					OutputArray5 = PGMObject.FunctionComposit2(OutputArray4, InverseBeta5);
					break;
				case 1:
					InverseBeta5 = PGMObject.FunctionInverse(Row5Beta1);
					OutputArray5 = PGMObject.FunctionComposit2(OutputArray4, InverseBeta5);
					break;
				case 2:
					InverseBeta5 = PGMObject.FunctionInverse(Row5Beta2);
					OutputArray5 = PGMObject.FunctionComposit2(OutputArray4, InverseBeta5);
					break;
				case 3:
					InverseBeta5 = PGMObject.FunctionInverse(Row5Beta3);
					OutputArray5 = PGMObject.FunctionComposit2(OutputArray4, InverseBeta5);
					break;
				case 4:
					InverseBeta5 = PGMObject.FunctionInverse(Row5Beta4);
					OutputArray5 = PGMObject.FunctionComposit2(OutputArray4, InverseBeta5);
					break;
				case 5:
					InverseBeta5 = PGMObject.FunctionInverse(Row5Beta5);
					OutputArray5 = PGMObject.FunctionComposit2(OutputArray4, InverseBeta5);
					break;
					
			}
			
			int Temp5 = 0;
			int Addition6 = 0;
			if (OutputArray5[5] == Row6Beta0[5])
			{
				Temp5 = 0;
				Addition6 = Row6PM[0];
			}
			if (OutputArray5[5] == Row6Beta1[5])
			{
				Temp5 = 1;
				Addition6 = Row6PM[1];
			}
			if (OutputArray5[5] == Row6Beta2[5])
			{
				Temp5 = 2;
				Addition6 = Row6PM[2];
			}
			if (OutputArray5[5] == Row6Beta3[5])
			{
				Temp5 = 3;
				Addition6 = Row6PM[3];
			}
			if (OutputArray5[5] == Row6Beta4[5])
			{
				Temp5 = 4;
				Addition6 = Row6PM[4];
			}
			
			int InverseBeta6[] = new int[10];
			int OutputArray6[] = new int[10];
			switch (Temp5)
			{
				case 0:
					InverseBeta6 = PGMObject.FunctionInverse(Row6Beta0);
					OutputArray6 = PGMObject.FunctionComposit2(OutputArray5, InverseBeta6);
					break;
				case 1:
					InverseBeta6 = PGMObject.FunctionInverse(Row6Beta1);
					OutputArray6 = PGMObject.FunctionComposit2(OutputArray5, InverseBeta6);
					break;
				case 2:
					InverseBeta6 = PGMObject.FunctionInverse(Row6Beta2);
					OutputArray6 = PGMObject.FunctionComposit2(OutputArray5, InverseBeta6);
					break;
				case 3:
					InverseBeta6 = PGMObject.FunctionInverse(Row6Beta3);
					OutputArray6 = PGMObject.FunctionComposit2(OutputArray5, InverseBeta6);
					break;
				case 4:
					InverseBeta6 = PGMObject.FunctionInverse(Row6Beta4);
					OutputArray6 = PGMObject.FunctionComposit2(OutputArray5, InverseBeta6);
					break;
					
			}
			
			int Temp6 = 0;
			int Addition7 = 0;
			if (OutputArray6[6] == Row7Beta0[6])
			{
				Temp6 = 0;
				Addition7 = Row7PM[0];
			}
			if (OutputArray6[6] == Row7Beta1[6])
			{
				Temp6 = 1;
				Addition7 = Row7PM[1];
			}
			if (OutputArray6[6] == Row7Beta2[6])
			{
				Temp6 = 2;
				Addition7 = Row7PM[2];
			}
			if (OutputArray6[6] == Row7Beta3[6])
			{
				Temp6 = 3;
				Addition7 = Row7PM[3];
			}
			
			int InverseBeta7[] = new int[10];
			int OutputArray7[] = new int[10];
			switch (Temp6)
			{
				case 0:
					InverseBeta7 = PGMObject.FunctionInverse(Row7Beta0);
					OutputArray7 = PGMObject.FunctionComposit2(OutputArray6, InverseBeta7);
					break;
				case 1:
					InverseBeta7 = PGMObject.FunctionInverse(Row7Beta1);
					OutputArray7 = PGMObject.FunctionComposit2(OutputArray6, InverseBeta7);
					break;
				case 2:
					InverseBeta7 = PGMObject.FunctionInverse(Row7Beta2);
					OutputArray7 = PGMObject.FunctionComposit2(OutputArray6, InverseBeta7);
					break;
				case 3:
					InverseBeta7 = PGMObject.FunctionInverse(Row7Beta3);
					OutputArray7 = PGMObject.FunctionComposit2(OutputArray6, InverseBeta7);
					break;
					
			}
			
			int Temp7 = 0;
			int Addition8 = 0;
			if (OutputArray7[7] == Row8Beta0[7])
			{
				Temp7 = 0;
				Addition8 = Row8PM[0];
			}
			if (OutputArray7[7] == Row8Beta1[7])
			{
				Temp7 = 1;
				Addition8 = Row8PM[1];
			}
			if (OutputArray7[7] == Row8Beta2[7])
			{
				Temp7 = 2;
				Addition8 = Row8PM[2];
			}
			
			int EncryptedSeed = Addition1 + Addition2 + Addition3 + Addition4 + Addition5 + Addition6 + Addition7 + Addition8;
			
			KeyStream[ik - 2000] = EncryptedSeed;
		}
		return KeyStream;
	}
	
	int[] FunctionComposit(int Array1[], int Array2[], int Array3[], int Array4[], int Array5[], int Array6[], int Array7[], int Array8[])
	{
		int TempArray[] = new int[10];
		for (int i = 1; i < 11; i++)
		{
			TempArray[i - 1] = Array1[Array2[Array3[Array4[Array5[Array6[Array7[Array8[i - 1] - 1] - 1] - 1] - 1] - 1] - 1] - 1];
		}
		return TempArray;
	}
	
	int[] FunctionComposit2(int Array1[], int Array2[])
	{
		int TempArray[] = new int[10];
		for (int i = 1; i < 11; i++)
		{
			TempArray[i - 1] = Array2[Array1[i - 1] - 1];
		}
		return TempArray;
	}
	
	int[] FunctionInverse(int Array[])
	{
		int TempArray[] = new int[11];
		int TempArray1[] = new int[10];
		for (int i = 0; i < 10; i++)
		{
			TempArray[Array[i]] = i + 1;
		}
		for (int i = 0; i < 10; i++)
		{
			TempArray1[i] = TempArray[i + 1];
		}
		return TempArray1;
	}
}