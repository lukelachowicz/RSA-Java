import java.util.Random;

public class RSA {
	/**
	 * Find the multiplicative inverse of a long int using extended Euclidean Algorithm
	 * @author Kayla Weldon
	 * @param e
	 * @param m
	 * @return inverse of e, mod m
	 * @throws ArithmeticException
	 */
	public long inverse(long e, long m) {
        long[] result = extendedEuclidean(e, m);
        long gcd = result[0];
        long x = result[1];

        if (gcd != 1) {
            // inverse doesn't exist if e and m are not co-prime
            throw new ArithmeticException("Inverse doesn't exist");
        } else {
			//returns positive result
			return (x % m + m) % m;
        }
    }

	/**
	 * calculates the extended Euclidean algorithm for two long integers.
	 * @author Kayla Weldon
	 * @param a first integer
	 * @param b second integer
	 * @return array containing GCD, x, and y so that ax + by = GCD(a,b)
	 */
    private long[] extendedEuclidean(long a, long b) {
        if (b == 0) {
			// base case: GCD(a, 0) = a, x = 1, y = 0
            return new long[]{a, 1, 0};
        } else {
			// recursive case: GCD(a, b) = GCD(b, a mod b)
            long[] result = extendedEuclidean(b, a % b);
            long gcd = result[0];
            long x = result[2];
            long y = result[1] - (a / b) * result[2];
            return new long[]{gcd, x, y};
        }
    }
	
	/**
     * Display an array of longs on stdout
     * @param cipher
     * @author Luke Lachowicz
     */
    public void show(long[] cipher)
    {
        // loop through each position in array.
        for (long longArray : cipher) {
            
            // prints the array of longs.
            System.out.print(longArray);
        }
    }

	/**
	 * Raise a number, b, to a power, p, modulo m
	 * @author Kayla Weldon
	 * @param b base
	 * @param p exponent
	 * @param m modulo
	 * @return b^p mod m
	 */
	public long modPower(long b, long p, long m) {
		if (p == 0) {
			return 1;
		}
		if (p == 1) {
			return b;
		}
		return modPower(b, p/2, m) * modPower(b, p/2, m) * modPower(b, p%2, m) % m;
	}

	/**
     * Find a random prime number
     * @param m, n, rand
     * @return a random prime in the range m..n, using rand to generate the number
     * @author Luke Lachowicz
     * 
     */
    public long randPrime(int m, int n, Random rand) 
    {
        while (true) {
            // creates random number between m and n.
            long randomNum = m + rand.nextInt(n - m + 1);
            // if statement to make sure the number generated is an odd number.
            if (randomNum % 2 == 0) {
                randomNum++;
            }
           // primeNum is initially true.
            boolean primeNum = true;
            
            // checks divisibility from odd numbers until square root of randomNum.
            for (long i = 3; i * i <= randomNum; i+= 2) {
                
                // checks if randomNum is divisible by i. If so, then primeNum = false.
                if (randomNum % i == 0) {
                    primeNum = false;
                    break;
                }
            }
            
            // checks to make sure the numbers are not equal to 1 and are prime.
            if (primeNum && randomNum != 1) {
                // returns the prime number.
                return randomNum;
            }
        }
       
    }

	/**
     * Find a random number relatively prime to a given long int
     * @param n, rand
     * @return a random number relatively prime to n
     * @author Luke Lachowicz
     */
    public long relPrime(long n, Random rand) 
    {
        long randomNum;
        boolean primeNum;
        
        do {
            // generates number in the range from 2 to n-1.
            randomNum = (long) (rand.nextDouble() * (n - 2)) + 2;
            // primeNum is initially true.
            primeNum = true;
            // check for divisibility up to square root of randomNum.
            for (long i = 2; i * i <= randomNum; i++) {
                
                // If randomNum and n are divisible by i, then they are not relatively prime.
                if (randomNum % i == 0 && n % i == 0) {
                    primeNum = false;
                    break;
                }
            }
            // while - loop repeats until a relatively prime number is found
            // and returns the randomNum when complete.
        } while (!primeNum);
        return randomNum;
        
    }

	/**
     * Convert two numeric chars to long int
     * @param msg, p
     * @return the two digit number beginning at position p of msg as a long int
     * @author Luke Lachowicz
     */
    public long toLong(String msg, int p) 
    {
        // if statement to make sure p is not less than zero or has insufficient length.
        // returns 0 if this is the case.
        if (p < 0 || p + 1 >= msg.length()) {
            return 0;
        }
        
         // create temp to store p.
         int temp = (int) msg.charAt(p);
         // shift to the left 8 bits.
         temp = temp << 8;
         // result of integer representation of msg.
         temp = temp + msg.charAt(p+1);
        
         // shift to the right 8 bits.
        char a = (char) (temp >> 8);
         // bitwise AND to mask out all bits except for the 8 low-order bits.
        char b = (char) (temp & 0xFF);
        
        // checks if a and b are numbers and returns 0 if they are not.
        if (!Character.isDigit(a) || !Character.isDigit(b)) {
            return 0;
        }
        
        // concatenates a and b into a string.
        String number = "" + a + b;
        
        // returns the number and converts it to a long.
        return Long.parseLong(number);
    }

	/**
     * Convert a long to 2 chars
     * @param x
     * @return the string made up of two numeric digits representing x
     * @author Luke Lachowicz
     */
    public String longTo2Chars(long x) 
    {
        // shift right 8 bits.
        int firstChar = (char) x >> 8;
        // bitwise AND to mask out all bits except for the 8 low-order bits.
        int secondChar = (char) x & 0x000000000000ffff;
        // concatenate firstChar + secondChar to create the string.
        String result = "" + firstChar + secondChar;
        // returns the result.
        return result;
    }
}
