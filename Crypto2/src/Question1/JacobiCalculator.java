// Jacobi Calculator.

package Question1;

import java.util.Scanner;

public class JacobiCalculator
{
	public static void main(String[] args)
	{
		Scanner Sca = new Scanner(System.in);
		
		// Enter the suitable inputs.
		System.out.println(" Enter the value of Numerator : ");
		int Numerator = Sca.nextInt();
		
		System.out.println("Enter the value of Denominator : ");
		int Denominator = Sca.nextInt();
		int Answer = 1;
		
		// Call the JacobiSymbol function.
		int JacobiValue = JacobiSymbol(Numerator, Denominator, Answer);
		System.out.println("Jacobi Value is : " + JacobiValue);
	}
	
	public static int JacobiSymbol(int m, int n, int Answer)
	{
		System.out.println(" Value of Numerator is " + m + " and value of Denomenator is " + n + " with Answer = " + Answer);
		
		// If numerator is 0.
		if (m == 0)
		{
			Answer = Answer * 0;
			return Answer;
		}
		
		// If numerator is 1.
		else if (m == 1)
		{
			Answer = Answer * 1;
			return Answer;
		}
		
		// In numerator is divisible by 2.
		else if (m % 2 == 0)
		{
			// If numerator is not 2.
			if (m != 2)
			{
				// Divide the fraction into two parts i.e. 2/n and (m/2)/n and
				// compute them separately using recursion.
				m = m / 2;
				Answer = JacobiSymbol(2, n, Answer);
				Answer = JacobiSymbol(m, n, Answer);
			}
			// If m=2.
			else if (m == 2)
			{
				// Check if 2/n is to be replaced by 1 or -1.
				if (n % 8 == 1)
				{
					Answer = Answer * 1;
					return Answer;
				}
				else
				{
					int Temp1 = n;
					int Temp2 = Temp1 / 8;
					Temp2 = Temp2 + 1;
					int Temp3 = 8 * Temp2;
					int Final = Temp1 - Temp3;
					if (Final == -1)
					{
						Answer = Answer * 1;
						return Answer;
					}
					else
					{
						Answer = Answer * -1;
						return Answer;
					}
				}
			}
		}
		// If n > m, then swap the numbers.
		else if (n > m)
		{
			if (m % 4 == 3 && n % 4 == 3)
			{
				int Temp;
				Temp = m;
				m = n;
				n = Temp;
				Answer = Answer * -1;
			}
			else
			{
				int Temp;
				Temp = m;
				m = n;
				n = Temp;
				Answer = Answer * 1;
			}
			Answer = JacobiSymbol(m, n, Answer);
		}
		// If m > n then do m/n = (m%n)/n.
		else if (m % 2 != 0 && m > n)
		{
			m = m % n;
			Answer = JacobiSymbol(m, n, Answer);
		}
		return Answer;
	}
}
