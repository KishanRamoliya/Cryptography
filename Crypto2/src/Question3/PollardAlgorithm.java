// Pollard Algorithm implementation.

package Question3;

import java.math.BigInteger;

public class PollardAlgorithm
{
	public static void main(String[] args)
	{
		// Pollard p-1 algorithm to find factorization of BigInteger NN.
		System.out.println(" We have the value of NN. ");
		BigInteger NN = new BigInteger("68102916241556970724365932142686835003312542409731911391");
		System.out.println(" Value of NN : " + NN);
		
		BigInteger ConstantOfOne = BigInteger.ONE;
		
		System.out.println(" Please Wait...System is computing the factors.");
		
		BigInteger LimitTwo = BigInteger.valueOf(2);
		
		BigInteger Factor = null;
		
		for (int j = 2; j < 5556; j++)
		{
			LimitTwo = (LimitTwo.pow(j)).mod(NN);
			BigInteger Temp = LimitTwo.subtract(ConstantOfOne);
			Factor = Temp.gcd(NN);
			if (Temp.compareTo(ConstantOfOne) == 1 && Temp.compareTo(NN) == -1)
			{
				if ((Factor.mod(LimitTwo)).compareTo(LimitTwo) != 0 && (Factor.mod(LimitTwo)).compareTo(ConstantOfOne) != 0)
				{
					break;
				}
			}
			
		}
		
		System.out.println(" Factor : " + Factor);
	}
	
}
