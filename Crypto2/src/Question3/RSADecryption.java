// RSA implementation.

package Question3;

import java.math.BigInteger;
import java.util.Scanner;

public class RSADecryption
{
	public static void main(String[] args)
	{
		// Value of NN.
		System.out.println(" We have the value of NN. ");
		Scanner Sca = new Scanner(System.in);
		BigInteger NN = new BigInteger("68102916241556970724365932142686835003312542409731911391");
		System.out.println(" Value of NN : " + NN);
		
		BigInteger ConstantOfOne = BigInteger.ONE;
		
		// From the Pollard p-1 algorithm, one of the factor is
		// 761059198034100157.
		BigInteger Factor = new BigInteger("761059198034100157");
		System.out.println(" Factor : " + Factor);
		System.out.println();
		
		// Parameters for RSA algorithm.
		System.out.println(" RSA algorithm implementation. ");
		
		// n in algorithm.
		BigInteger n = NN;
		System.out.println(" Value of n : " + n);
		
		// p in RSA algorithm.
		BigInteger p = Factor;
		System.out.println(" Value of p : " + p);
		
		// q in RSA algorithm.
		BigInteger q = n.divide(p);
		System.out.println(" Value of q : " + q);
		
		// (p - 1)*(q - 1) in RSA algorithm.
		BigInteger Temp = (p.subtract(ConstantOfOne)).multiply(q.subtract(ConstantOfOne)); // nn=(p-1)(q-1)
		System.out.println(" Value of (p - 1)*(q - 1) : " + Temp);
		
		// e in RSA algorithm.
		System.out.println(" We have the value of exponent i.e. e. ");
		BigInteger Exponent = new BigInteger("36639088738407540894550923202224101809992059348223191299");
		System.out.println(" Exponent i.e. e : " + Exponent);
		
		// d in RSA algorithm.
		BigInteger PrivateKey = Exponent.modInverse(Temp);
		System.out.println(" Private Key i.e. d : " + PrivateKey);
		
		// Decryption process.
		String FinalOutput = "";
		Boolean Choice = false;
		Scanner Sca1 = new Scanner(System.in);
		while (!Choice)
		{
			System.out.println();
			// Enter Ciphertext.
			System.out.println(" Enter the cipher text : ");
			BigInteger CipherText = Sca.nextBigInteger();
			
			// Obtain plain text in index form.
			BigInteger PlainText = CipherText.modPow(PrivateKey, NN);
			System.out.println(" Plain Text :" + PlainText);
			
			// For simplicity convert it to string.
			String FinalPlainText = "";
			FinalPlainText = PlainText.toString();
			
			// Add 0 to if text length is odd.
			if (FinalPlainText.length() % 2 != 0)
			{
				FinalPlainText = "0" + FinalPlainText;
			}
			
			System.out.println(" Final Plain Text :" + FinalPlainText);
			
			char Matrix[][] = { { ' ', '*', '4', '>', 'H', 'R', '\\', 'f', 'o', 'x' }, { '!', '+', '5', '?', 'I', 'S', ']', 'g', 'p', 'y' }, { '\\', ',', '6', '@', 'J', 'T', '^', 'h', 'q', 'z' },
					{ '#', '-', '7', 'A', 'K', 'U', '_', 'i', 'r', '{' }, { '$', '.', '8', 'B', 'L', 'V', '`', 'j', 's', '|' }, { '%', '/', '9', 'C', 'M', 'W', 'a', 'k', 't', '}' },
					{ '&', '0', ':', 'D', 'N', 'X', 'b', 'l', 'u', '~' }, { '\'', '1', ';', 'E', 'O', 'Y', 'c', 'm', 'v', ' ' }, { '(', '2', '<', 'F', 'P', 'Z', 'd', 'n', 'w', ' ' },
					{ ')', '3', '=', 'G', 'Q', '[', 'e', '\\', 'n', ' ' } };
					
			// Get the index <Digit 1>=Row index and <Digit 2>= Column index and
			// replace the characters with the one given in matrix.
			for (int i = 0; i < FinalPlainText.length(); i = i + 2)
			{
				FinalOutput = FinalOutput + Matrix[Character.getNumericValue(FinalPlainText.charAt(i))][Character.getNumericValue(FinalPlainText.charAt(i + 1))];
			}
			
			// For entering multiple cipher texts.
			// If you enter Yes, then it will allow you to enter another cipher
			// text.
			// If you enter No, then program will terminate and Plain text will
			// be displayed.
			System.out.println(" Enter Yes to continue and No to finish the decryption. ");
			// Scanner Sca1 = new Scanner(System.in);
			System.out.println(" Enter Yes or No : ");
			String TempChoice = Sca1.nextLine();
			Choice = TempChoice.equals("No");
			if (Choice)
			{
				break;
			}
		}
		System.out.println(" Readable Plain Text : " + FinalOutput);
	}
}
