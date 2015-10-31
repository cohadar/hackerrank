public class Utils {

	public static long modPow(long a, long b, long mod)
	{
		long res = 1;
		long pow = a;
		while(b > 0) {
			if ((b & 1) == 1) {
				res = (pow * res) % mod;
			}
			pow = (pow * pow) % mod;
			b /= 2;
		}
		return res;
	}

}