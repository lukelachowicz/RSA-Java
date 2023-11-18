import java.util.Random;

public class Person {
	private long modulus;  // The public modulus 'm'
	private long pub_exp;  // The public exponent 'e'
	private long priv_exp;  // The private exponent 'd' aka the key
	private RSA rsa;

	public Person() {
		rsa = new RSA();
		long p = rsa.randPrime(1000, 5000, new Random());  // First prime number. NOTE: Arguments need to be adjusted.
		long q = rsa.randPrime(1000, 5000, new Random());  // Second prime number. NOTE: Arguments need to be adjusted.
		while (p == q)  // Make sure to not have p and q be the same. NOTE: More research needed to determine whether p and q can be the same or not.
			q = rsa.randPrime(1000, 5000, new Random());  // NOTE: Arguments need to be adjusted.
		this.modulus = p * q;
		long N = (p - 1) * (q - 1);
		this.pub_exp = rsa.relPrime(N, new Random());
		this.priv_exp = rsa.inverse(pub_exp, N);
	}

	public long getM() {
		return modulus;
	}

	public long getE() {
		return pub_exp;
	}

	public long[] encryptTo(String msg, Person recipient) {
		long[] ciphertext = new long[msg.length()];
		for (int i = 0; i < msg.length(); i++) {
			long num_char = (long) msg.charAt(i);
			ciphertext[i] = rsa.modPower(num_char, recipient.getE(), recipient.getM());
		}
		return ciphertext;
	}

	public String decrypt(long[] cipher) {
		String plaintext = "";
		for (int i = 0; i < cipher.length; i++) {
			long cipher_char = cipher[i];
			plaintext += rsa.modPower(cipher_char, priv_exp, modulus);
		}
		return plaintext;
	}
}
