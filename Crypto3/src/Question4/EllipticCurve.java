Ellimpic curve implementation.

package Question4;

import java.math.BigInteger;
import java.util.Scanner;

public class EllipticCurve
{
	public static void main(String args[])
	{
		// Given a.
		BigInteger a = new BigInteger("2");
		BigInteger Temp = new BigInteger("3");
		BigInteger First[] = new BigInteger[40];
		BigInteger Second[] = new BigInteger[40];
		// Given Z31.
		BigInteger Modulus = new BigInteger("31");
		BigInteger Slop;
		First[1] = a;
		Second[1] = new BigInteger("9");
		// Generating other points and finding slops.
		for (int i = 2; i < 39; i++)
		{
			if (i == 2)
			{
				BigInteger X2 = First[1].multiply(First[1]);
				BigInteger Slop1 = (Temp.multiply(X2)).add(a);
				BigInteger Slop2 = (a.multiply(Second[1])).modInverse(Modulus);
				Slop = (Slop1.multiply(Slop2)).mod(Modulus);
			}
			else
			{
				First[2] = new BigInteger("10");
				Second[2] = new BigInteger("2");
				BigInteger Slop1 = Second[1].subtract(Second[i - 1]);
				BigInteger Slop2 = (First[1].subtract(First[i - 1])).modInverse(Modulus);
				Slop = (Slop1.multiply(Slop2)).mod(Modulus);
			}
			if (i == 2)
			{
				First[i] = ((((Slop.multiply(Slop)).subtract(First[1]))).subtract(First[1])).mod(Modulus);
				Second[i] = ((Slop.multiply(First[1].subtract(First[i]))).subtract(Second[1])).mod(Modulus);
			}
			else
			{
				First[2] = new BigInteger("10");
				Second[2] = new BigInteger("2");
				First[i] = ((((Slop.multiply(Slop)).subtract(First[i - 1]))).subtract(First[1])).mod(Modulus);
				Second[i] = ((Slop.multiply(First[i - 1].subtract(First[i]))).subtract(Second[i - 1])).mod(Modulus);
			}
		}
		String FinalPlainText = "";
		int Flag = 0;
		while (Flag == 0)
		{
			// Take input of cipher text's coordinates.
			Scanner Sca = new Scanner(System.in);
			System.out.println(" Enter x1 for X : ");
			BigInteger InputX1 = Sca.nextBigInteger();
			System.out.println(" Enter y1 for X : ");
			BigInteger InputY1 = Sca.nextBigInteger();
			System.out.println(" Enter Y : ");
			BigInteger InputY = Sca.nextBigInteger();
			// Decryption.
			for (int i = 1; i < 39; i++)
			{
				BigInteger PlainTextX = null;
				if (InputX1.equals(First[i]))
				{
					if (InputY1.equals(new BigInteger("1")))
					{
						if (!(Second[i].mod(a)).equals(new BigInteger("0")))
						{
							int Status = (8 * i) % 39;
							PlainTextX = First[Status];
							BigInteger plain_text = (InputY.multiply(PlainTextX.modInverse(Modulus))).mod(Modulus);
							int PlainTextInt = plain_text.intValue() + 64;
							// Converting to characters.
							FinalPlainText = FinalPlainText + Character.toString((char) PlainTextInt);
						}
					}
					else
					{
						if ((Second[i].mod(a)).equals(new BigInteger("0")))
						{
							int Status = (8 * i) % 39;
							PlainTextX = First[Status];
							BigInteger plain_text = (InputY.multiply(PlainTextX.modInverse(Modulus))).mod(Modulus);
							int PlainTextInt = plain_text.intValue() + 64;
							// Converting to characters.
							FinalPlainText = FinalPlainText + Character.toString((char) PlainTextInt);
						}
					}
				}
			}
			System.out.println(" Enter 0 to continue and 1 to stop. ");
			System.out.println(" Enter Choice : ");
			Flag = Sca.nextInt();
			if (Flag == 1)
			{
				System.out.println(" Plain text Character : " + FinalPlainText);
			}
		}
	}
}
