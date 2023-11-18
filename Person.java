import java.util.Random;

/**
 * The Person class holds the public and secret RSA keys. The class is used to encrypt and decrypt to and from, respectively, another Person object.
 * @author Pial Das
 */
public class Person {
	private long modulus;  // The public modulus 'm'
	private long pub_exp;  // The public exponent 'e'
	private long priv_exp;  // The private exponent 'd' aka the secret key
	private RSA rsa;

	/**
	 * Create a Person object with a new public modulus and a new public exponent as part of its public key, and a private exponent as part of their secret key.
	 */
	public Person() {
		rsa = new RSA();
		long p = rsa.randPrime(1000, 5000, new Random());  // First prime number. NOTE: Arguments need to be adjusted.
		long q = rsa.randPrime(1000, 5000, new Random());  // Second prime number. NOTE: Arguments need to be adjusted.
		while (p == q)  // Make sure to not have p and q be the same. NOTE: More research needed to determine whether p and q can be the same or not.
			q = rsa.randPrime(1000, 5000, new Random());  // NOTE: Arguments need to be adjusted.
		this.modulus = p * q;  // Public modulus is obtained by multiplying 2 large prime numbers
		long N = (p - 1) * (q - 1);  // Private modulus is used to create the private exponent for decryption
		this.pub_exp = rsa.relPrime(N, new Random());  // The public exponent must be relatively prime to the private modulus
		this.priv_exp = rsa.inverse(pub_exp, N);
	}

	/**
	 * Return the public modulus, m, from the class's public key.
	 * @return the public modulus
	 * @author Pial Das
	 */
	public long getM() {
		return modulus;
	}

	/**
	 * Return the public exponent, e, from the class's public key.
	 * @return the public exponent
	 * @author Pial Das
	 */
	public long getE() {
		return pub_exp;
	}

	/**
	 * Create a ciphertext from a given string for a recipient.
	 * @param msg a given string as the plaintext
	 * @param recipient another Person object for the ciphertext to be created for
	 * @return an array of longs as the ciphertext
	 * @author Pial Das
	 */
	public long[] encryptTo(String msg, Person recipient) {
		long[] ciphertext = new long[msg.length()];
		for (int i = 0; i < msg.length(); i++) {  // Iterate over each character in the string, convert the character to its ASCII value, and use the RSA encryption formula to get a new value. Store the value in an array of longs.
			long num_char = (long) msg.charAt(i);
			ciphertext[i] = rsa.modPower(num_char, recipient.getE(), recipient.getM());
		}
		return ciphertext;
	}

	/** Decrypt a ciphertext to plaintext. It is assumed that the ciphertext was created using this object's public key.
	 * @param cipher an array of longs as the ciphertext created by another Person object
	 * @return a string as the plaintext
	 * @author Pial Das
	 */
	public String decrypt(long[] cipher) {
		String plaintext = "";
		for (int i = 0; i < cipher.length; i++) {  // Iterate over each long in the array (which is ciphertext representing a character) and decrypt using the RSA decryption formula. The result will be stored in a string.
			long cipher_char = cipher[i];
			plaintext += rsa.modPower(cipher_char, priv_exp, modulus);  // The RSA decryption formula with the result casted as a char and appended to the string.
		}
		return plaintext;
	}
}
