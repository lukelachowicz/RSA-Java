import java.util.Random;

public class RSA {
	public long inverse(long e, long m) {
		return 0;
	}

	public void show(long[] cipher) {
	}

	public static long modPower(long b, long p, long m) {
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

	public static void main(String[] args) {
		long result = modPower(7, 2, 47);
		System.out.println(result);
	}
}
