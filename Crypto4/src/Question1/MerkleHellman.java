// Merkle Hellman implementation.

package Question1;

import java.math.BigInteger;
import java.util.Scanner;

public class MerkleHellman
{
	public static void main(String[] args)
	{
		System.out.println(" Merkle-Hellman knapsack system. ");
		System.out.println();
		BigInteger BigIntM = new BigInteger("2036764117802210446778721319780021001");
		BigInteger M = BigIntM.nextProbablePrime();
		BigInteger BigIntW = new BigInteger("127552671440279916013001");
		BigInteger W = BigIntW.nextProbablePrime();
		BigInteger InverseW = W.modInverse(M);
		BigInteger PublicKeys[] = { new BigInteger("7379441564850969401451185884533"), new BigInteger("56378280776603722877755282"), new BigInteger("112884114224647725671523585"),
				new BigInteger("60452387844955357941794678553967454"), new BigInteger("1658184728723638908169273"), new BigInteger("30226193922350126299457059360970706"),
				new BigInteger("1627613290730052207554796267157973143"), new BigInteger("1801681484093953813683921625"), new BigInteger("3689721101307163301425382974819"),
				new BigInteger("59035535069861184017207807336684"), new BigInteger("450516035527068663357990172"), new BigInteger("3571474800327837648364588"),
				new BigInteger("472284279921126114936262878628367"), new BigInteger("1844860359324574490292817467878"), new BigInteger("944568559714699558432245841243713"),
				new BigInteger("14413069214737309669723333937"), new BigInteger("483619102758622442162835189103635464"), new BigInteger("900904518382697046799967323"),
				new BigInteger("3778274240261877619572062441118083"), new BigInteger("28061587716861581522864620"), new BigInteger("1889137120194715145506171178565552"),
				new BigInteger("1218462463657766415659430934619911908"), new BigInteger("236142140151892064628551313333715"), new BigInteger("15113096961302615821168809596498374"),
				new BigInteger("800321619026644769080281098919604918"), new BigInteger("1832188704265876221823878233636971208"), new BigInteger("29517767152272577687764155629279"),
				new BigInteger("461215026054807902433246360459"), new BigInteger("241809551379183668409977314635804711"), new BigInteger("7206853489047255534651699521"),
				new BigInteger("225002912420653771846969044"), new BigInteger("115303660849198395398374580349"), new BigInteger("892868700081959412091147"),
				new BigInteger("1934476411034489768651340756414541856"), new BigInteger("57651639095592037279313270643"), new BigInteger("922430307214958685426324746960"),
				new BigInteger("13903241186990510845419289"), new BigInteger("120904775689272952526387957527869803"), new BigInteger("230607704356411111636497199761"),
				new BigInteger("28825883324131738779614641832"), new BigInteger("14758884022570638884861783860213"), new BigInteger("7270502272095955212742197"),
				new BigInteger("3602980310173586787619804187"), new BigInteger("400160809513705042554461389207841522"), new BigInteger("118071070267275039474695530686389"),
				new BigInteger("967238205517244884325670378207270928"), new BigInteger("7556548480778860582024684714262208") };
		int ArrayLenght = PublicKeys.length;
		BigInteger PI[] = new BigInteger[47];
		for (int i = 0; i < ArrayLenght; i++)
		{
			PI[i] = (PublicKeys[i].multiply(InverseW)).mod(M);
		}
		BigInteger[] NewPublicKey = new BigInteger[47];
		for (int j = 0; j < ArrayLenght; j++)
		{
			NewPublicKey[j] = PI[j];
		}
		BigInteger Temp;
		for (int i = 0; i < ArrayLenght - 1; i++)
		{
			for (int j = 0; j < ArrayLenght - 1; j++)
			{
				if (NewPublicKey[j].compareTo(NewPublicKey[j + 1]) == 1)
				{
					Temp = NewPublicKey[j];
					NewPublicKey[j] = NewPublicKey[j + 1];
					NewPublicKey[j + 1] = Temp;
				}
			}
		}
		int NewPI[] = new int[47];
		for (int i = 0; i < ArrayLenght; i++)
		{
			for (int j = 0; j < ArrayLenght; j++)
			{
				if (PI[i].compareTo(NewPublicKey[j]) == 0)
				{
					NewPI[i] = j;
					break;
				}
			}
		}
		char Matrix[][] = { { ' ', '*', '4', '>', 'H', 'R', '\\', 'f', 'o', 'x' }, { '!', '+', '5', '?', 'I', 'S', ']', 'g', 'p', 'y' }, { '\\', ',', '6', '@', 'J', 'T', '^', 'h', 'q', 'z' },
				{ '#', '-', '7', 'A', 'K', 'U', '_', 'i', 'r', '{' }, { '$', '.', '8', 'B', 'L', 'V', '`', 'j', 's', '|' }, { '%', '/', '9', 'C', 'M', 'W', 'a', 'k', 't', '}' },
				{ '&', '0', ':', 'D', 'N', 'X', 'b', 'l', 'u', '~' }, { '\'', '1', ';', 'E', 'O', 'Y', 'c', 'm', 'v', ' ' }, { '(', '2', '<', 'F', 'P', 'Z', 'd', 'n', 'w', ' ' },
				{ ')', '3', '=', 'G', 'Q', '[', 'e', '\\', 'n', ' ' } };
		Scanner ScanText = new Scanner(System.in);
		String FinalPlainText = "";
		Boolean Choice = false;
		while (!Choice)
		{
			System.out.println(" Enter the Cipher text : ");
			BigInteger CipherText = ScanText.nextBigInteger();
			BigInteger Product = (InverseW.multiply(CipherText)).mod(M);
			int TempArray[] = new int[47];
			for (int i = ArrayLenght - 1; i >= 0; i--)
			{
				if (Product.compareTo(NewPublicKey[i]) >= 0)
				{
					TempArray[i] = 1;
					Product = Product.subtract(NewPublicKey[i]);
				}
				else
				{
					TempArray[i] = 0;
				}
			}
			int ArrayR[] = new int[47];
			for (int i = 0; i < ArrayLenght; i++)
			{
				int k = NewPI[i];
				ArrayR[i] = TempArray[k];
			}
			String PlainText = "";
			for (int i = 0; i < ArrayLenght; i++)
			{
				PlainText = PlainText + ArrayR[i];
			}
			BigInteger TempCipherText = new BigInteger(PlainText, 2);
			String StringCipherText = TempCipherText.toString();
			int MatrixColumn[] = new int[64];
			int MatrixRow[] = new int[64];
			while (StringCipherText.length() != 14)
			{
				StringCipherText = 0 + StringCipherText;
			}
			for (int i = 0; i < StringCipherText.length(); i = i + 2)
			{
				MatrixRow[i] = Integer.parseInt(Character.toString(StringCipherText.charAt(i)));
			}
			for (int j = 0; j < StringCipherText.length(); j = j + 2)
			{
				MatrixColumn[j] = Integer.parseInt(Character.toString(StringCipherText.charAt(j + 1)));
			}
			for (int i = 0; i < StringCipherText.length(); i = i + 2)
			{
				FinalPlainText = FinalPlainText + Matrix[Character.getNumericValue(StringCipherText.charAt(i))][Character.getNumericValue(StringCipherText.charAt(i + 1))];;
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
		System.out.println(" Readable Plain Text : " + FinalPlainText);
	}
}
