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

	public void show(long[] cipher) {
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

	public long randPrime(int m, int n, Random rand) {
		return 0;
	}

	public long relPrime(long n, Random rand) {
		return 0;
	}

	public long toLong(String msg, int p) {
		return 0;
	}

	public String longTo2Chars(long x) {
		return "";
	}
}