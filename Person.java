public class Person {
	private long modulus;  // The public modulus 'm'
	private long pub_exp;  // The public exponent 'e'
	private long priv_exp;  // The private exponent 'd' aka the key

	public Person(long modulus, long pub_exp, long priv_exp) {
		this.modulus = modulus;
		this.pub_exp = pub_exp;
		this.priv_exp = priv_exp;
	}

	public long getM() {
		return 0;
	}

	public long getE() {
		return 0;
	}

	public long[] encryptTo(String msg) {
		return new long[8];
	}

	public String decrypt(long[] cipher) {
		return "";
	}
}
